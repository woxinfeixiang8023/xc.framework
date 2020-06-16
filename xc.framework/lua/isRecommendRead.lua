--读的操作
--根据教师推荐教师信息
ngx.header.content_type="application/json;charset=utf8"
local uri_args = ngx.req.get_uri_args();
local isRecommend = uri_args["isRecommend"];
--获取本地缓存
local cache_ngx = ngx.shared.my_cache;
--根据ID 获取本地缓存数据
local isRecommendCache = cache_ngx:get('isRecommendIndex:'..isRecommend);
--本地缓存中没有数据
if isRecommendCache == "" or isRecommendCache == nil then
    --去redis中读取数据
    local redis = require("resty.redis");
    local red = redis:new()
    red:set_timeout(2000)
    red:connect("127.0.0.1", 6379)
    red:auth("ok")
    --读取redis中的数据
    local rescontent=red:get("isRecommendIndex:"..isRecommend);
    --如果redis中不存在数据
    if ngx.null == rescontent then
        local cjson = require("cjson");
        --读取db数据
        local mysql = require("resty.mysql");
        local db = mysql:new();
        db:set_timeout(2000)
        local props = {
            host = "127.0.0.1",
            port = 3306,
            database = "xc_user",
            user = "root",
            password = "root"
        }
        local res = db:connect(props);
        local select_sql = "SELECT * FROM `xc_user`.`xc_teacher` WHERE isRecommend = "..isRecommend
        res = db:query(select_sql)
        --将数据转换成json对象
        local responsejson = cjson.encode(res);
        --存储到redis中
        red:set("isRecommendIndex:"..isRecommend,responsejson);
        ngx.say(responsejson);
        db:close()
    else
        --将数据存储到本地缓存中， 本地缓存代表就nginx
        cache_ngx:set("isRecommendIndex:"..isRecommend, rescontent, 10*60);
        ngx.say(rescontent)
    end
    red:close()
else
    --返回本地数据
    ngx.say(contentCache)
end






