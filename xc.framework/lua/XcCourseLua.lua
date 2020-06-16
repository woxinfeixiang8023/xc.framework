--写的文件
ngx.header.content_type="application/json;charset=utf8"
local cjson = require("cjson")
local mysql = require("resty.mysql")
local uri_args = ngx.req.get_uri_args()
local id = uri_args["id"]
local db = mysql:new()
db:set_timeout(1000)
local props = {
    host = "127.0.0.1",
    port = 3306,
    database = "xc_course",
    user = "root",
    password = "root"
}

local res = db:connect(props)
local select_sql = "select url,pic from xc_content where status ='1' and category_id="..id.." order by sort_order"
res = db:query(select_sql)
db:close()

local redis = require("resty.redis")
local red = redis:new()
red:set_timeout(2000)

local ip ="127.0.0.1"
local port = 6379
red:connect(ip,port)
red:auth("ok")
red:set("content_"..id,cjson.encode(res))
red:close()

--http://localhost:9000/updatecontent?id=1&name=admin
--传递多个参数的测试
ngx.say("{flag:true}")

