--读的操作
ngx.header.content_type="application/json;charset=utf8"
local uri_args = ngx.req.get_uri_args();
local id = uri_args["id"];
--获取本地缓存
local cache_ngx = ngx.shared.my_cache;
--根据ID 获取本地缓存数据
local contentCache = cache_ngx:get('content_cache_'..id);
--本地缓存中有数据
if contentCache == "" or contentCache == nil then
	--去redis中读取数据
    local redis = require("resty.redis");
    local red = redis:new()
    red:set_timeout(2000)
    red:connect("127.0.0.1", 6379)
	red:auth("ok")
	--读取redis中的数据
    local rescontent=red:get("content_"..id);
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
            database = "xc_course",
            user = "root",
            password = "root"
        }
        local res = db:connect(props);
        local select_sql = "select url,pic from xc_content where status ='1' and category_id="..id.." order by sort_order"
        res = db:query(select_sql)
		--将数据转换成json对象
        local responsejson = cjson.encode(res);
		--存储到redis中 
        red:set("content_"..id,responsejson);
        ngx.say(responsejson);
        db:close()
    else
		--将数据存储到本地缓存中， 本地缓存代表就nginx
        cache_ngx:set('content_cache_'..id, rescontent, 10*60);
        ngx.say(rescontent)
    end
    red:close()
else
	--返回本地数据
    ngx.say(contentCache)
end