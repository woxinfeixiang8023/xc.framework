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