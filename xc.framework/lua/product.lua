ngx.header.content_type="text/html;charset=utf8" --设置内容和页面字符编码
local uri_args = ngx.req.get_uri_args()	--获取请求url
local productId = uri_args["productId"]--获取请求参数
ngx.log(ngx.ERR,"json------1",productId)
local cache_ngx = ngx.shared.my_cache  --获取缓存对象
local productCacheKey = "product_info_"..productId  --拼接缓存的key
local productCache = cache_ngx:get(productCacheKey)	--查找缓存对象
if productCache == "" or productCache == nil then	--缓存不存在的情况
local http = require("resty.http")		--获取http模块
local httpc = http.new()				--获取http对象
--发起http请求到后台的java服务器
local resp, err = httpc:request_uri("http://127.0.0.1:8001/course",{
	method = "GET",
	path = "getProductList?key=isTop&item="..productId.."&grade=200003",
	keepalive=false
})
--获取响应结果
productCache = resp.body
--生成缓存的时间
local expireTime = math.random(600,1200)
--存储缓存数据到nginx
cache_ngx:set(productCacheKey,productCache,expireTime)
end

ngx.log(ngx.ERR,"json------1",productCache)

--获取json模块
local cjson = require("cjson")
--将数据转换成json对象
local productCacheJSON = cjson.decode(productCache)

ngx.say(productCacheJSON)

--将json对象中的数据， 转换成模板中的变量记录
local context = {
id = productCacheJSON.id,
goodsName = productCacheJSON.goodsName,
price = productCacheJSON.price,
goodsImg = productCacheJSON.goodsImg,
}
--获取模板模块
local template = require("resty.template")
--调用静态页面，将数据传递到静态模板中，并绑定数据
template.render("test.html", context)