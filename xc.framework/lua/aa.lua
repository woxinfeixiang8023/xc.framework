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
template.render("learing-article.html", context)







