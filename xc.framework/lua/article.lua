ngx.header.content_type="text/html;charset=utf8" --设置内容和页面字符编码
local uri_args = ngx.req.get_uri_args()	--获取请求url
local courseId = uri_args["courseId"]--获取请求参数
ngx.log(ngx.ERR,"json------1",courseId)
local cache_ngx = ngx.shared.my_cache  --获取缓存对象
local courseCacheKey = "course_info_"..courseId  --拼接缓存的key
local courseCache = cache_ngx:get(courseCacheKey)	--查找缓存对象
--if courseCache == "" or courseCache == nil then	--缓存不存在的情况
    local http = require("resty.http")		--获取http模块
    local httpc = http.new()				--获取http对象
    --发起http请求到后台的java服务器
    local resp, err = httpc:request_uri("http://127.0.0.1:8001/course/getTeachplanFormByCourseId/"..courseId)
    --获取响应结果
    courseCache = resp.body
    --生成缓存的时间
    local expireTime = math.random(600,1200)
    --存储缓存数据到nginx
    cache_ngx:set(courseCacheKey,courseCache,expireTime)
--end

ngx.log(ngx.ERR,"json------1",courseCache)
--获取json模块
local cjson = require("cjson")
--将数据转换成json对象
local courseCacheJSON = cjson.decode(courseCache)
--ngx.say(courseCache)
--ngx.say(courseCacheJSON.data.grade)
local grade1 = nil
if courseCacheJSON.data.grade=="200001" then
    grade1 = "初级"
end
if courseCacheJSON.data.grade=="200002" then
    grade1 = "中级"
end
if courseCacheJSON.data.grade=="200003" then
    grade1 = "高级"
end

local studymodel1 = nil
if courseCacheJSON.data.studymodel=="201001" then
    studymodel1 = "视频"
end
if courseCacheJSON.data.studymodel=="201002" then
    studymodel1 = "文档"
end

--将json对象中的数据， 转换成模板中的变量记录
local context = {
    id = courseCacheJSON.data.id,
    name = courseCacheJSON.data.name,
    mt = courseCacheJSON.data.mt,
    st = courseCacheJSON.data.st,
    grade = grade1,
    studymodel = studymodel1,
    description = courseCacheJSON.data.description,
    charge = courseCacheJSON.data.charge,
    valid = courseCacheJSON.data.valid,
    price = courseCacheJSON.data.price,
    priceOld = courseCacheJSON.data.priceOld,
    expires = courseCacheJSON.data.expires,
    expirationTime = courseCacheJSON.data.expirationTime,
    pic = courseCacheJSON.data.pic,
    users = courseCacheJSON.data.users,
    stuUsers = courseCacheJSON.data.stuUsers,
    timelength = courseCacheJSON.data.timelength,
    teacherName = courseCacheJSON.data.teacherName,
    intro = courseCacheJSON.data.intro,
    resume = courseCacheJSON.data.resume,
    teacherPic = courseCacheJSON.data.teacherPic
}
--获取模板模块
local template = require("resty.template")
--调用静态页面，将数据传递到静态模板中，并绑定数据
template.render("learing-article.html", context)






