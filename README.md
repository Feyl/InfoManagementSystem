# InfoManagementSystem
A simple student, teacher, class management system, based on JavaWeb and persistence layer handwritten ORM(simple MyBatis).

页面实现：
Semantic-UI、Html、CSS（Less）
JSP（EL表达式、JSTL标签库）:动态展示请求服务端获得的数据
JQuery、Ajax：异步访问、页面的局部更新

![login](https://raw.githubusercontent.com/GitHub-anonymousV/Gallery/master/GitHub-Imgs/login.y7i6uipanc0.png)
图1 登录页面

登录功能：
数据库表中记录了管理员的账号和密码，输入账号和密码点击登录，从数据库中查询账号或密码是否正确。账号密码正确，跳转到学生信息页面；不正确，跳转回当前登录页面并弹框提示“用户名或密码错误”。
登录成功后，会从服务器返回一个Cookie对象给浏览器存储。Cookie可以保存到硬盘（可以设置保存时间），作为已登录标记（每次客户端浏览器访问服务端时都会带着这个Cookie对象，服务端会根据这个Cookie对象去寻找之前创建的与之对应的Session对象），以备后续一定时间免登录验证。
待优化：表单校验
待添加：注册功能

登录校验功能：
	登录后服务端会存储一个Session对象，并在该Session对象添加一个登录标记，作为用户已登录的标记。客户端访问其他非登录页面的其他页面时，若之前未登录，会被拦截并跳转到登录页面，并提示“未登录，不能访问需要授权的页面”，这里通过Session（需要配合Cookie）和Filter过滤器实现。
其他服务端实现：
	通过Filter实现所有页面的同一编码设置，访问所有页面前（REQUEST请求、FORWARD请求转发、ERROR JSP页面错误跳转到错误处理页面、ASYNC 异步访问 等方式的访问）都会被拦截，然后设置服务端接收数据的字符编码类型（utf-8）以及相应客户端浏览器的响应格式（text/html）和字符编码类型（utf-8）。
	
 ![stuInfo](https://raw.githubusercontent.com/GitHub-anonymousV/Gallery/master/GitHub-Imgs/stuInfo.78kdwlvms040.png)
图2 查看学生信息页面

登录后或点击导航栏“查看学生信息”的超链接会跳转到该页面。跳转到该页面前会从数据库查询当前页面的信息并返回到页面。
点击编辑按钮弹出编辑框，如图3
 
 ![editStuInfo](https://raw.githubusercontent.com/GitHub-anonymousV/Gallery/master/GitHub-Imgs/editStuInfo.56aw3juxg280.png)
图3 学生信息编辑页面

学生信息修改功能：
	点击班级下拉框会从数据库查询当前数据库中班级表中存储的所有班级名称，并返回到浏览器，以下拉框的形式展示（通过JQuery、Ajax异步访问实现），此操作限制了学生可以选择的班级，建立起学生与班级的联系。
	学号不可修改（readonly属性）、其他的input框有（required属性）、若点击确认时为空、会提示 “未填写、需要填写”。点击确认，会在数据库修改对应学生的信息（根据学号（主键）修改），若入学日期输入不符合规范时，服务端会将入学日期设置为当前时间（通过正则表达式实现）。点击取消，模态框消失会返回学生信息页面。
待优化：表单校验
点击删除按钮，弹出确认是否删除的模态框，如图4
 
 ![delStuInfo](https://raw.githubusercontent.com/GitHub-anonymousV/Gallery/master/GitHub-Imgs/delStuInfo.q3x6wobm9g.png)
图4 学生信息删除页面

学生信息删除功能：
	点击是，会从数据库中删除相应学生的信息（根据学号（主键）删除）。点击否，模态框消失会返回学生信息页面。
 
 ![addStuInfo](https://raw.githubusercontent.com/GitHub-anonymousV/Gallery/master/GitHub-Imgs/addStuInfo.28x66xdahejo.png)
图5 添加学生信息页面

添加学生信息功能：
	实现细节与修改学生类似，不再赘述。
翻页查看功能：
	点击上一页或下一页按钮，如果符合条件（还有信息在待跳转页面未被显示），会进行页面跳转，反之，仍为当前页面。
	如何判断总页面数以及当前页面数值？登录后、点击查看学生信息、点击跳转页面按钮时都会从数据库查询总页面数以及从服务端计算当前页面数值并返回给客户端浏览器。
待优化：若无页面可跳转则给予弹框提示

 ![SearchStuInfo](https://raw.githubusercontent.com/GitHub-anonymousV/Gallery/master/GitHub-Imgs/SearchStuInfo.2x1q0zxvsfu0.png)
图6 查询学生信息页面

查询学生信息功能：
	在搜索框中输入查询学生的任何关键字（学号、姓名、院系、专业、班级、入学日期），按下回车键，服务端会先从数据库中查询所有学生的信息，后将所有匹配到的学生信息发送给客户端由浏览器显示。
查询效果，如图6
 
 ![searchStuInfoRes](https://raw.githubusercontent.com/GitHub-anonymousV/Gallery/master/GitHub-Imgs/searchStuInfoRes.1ohxvwxcs60w.png)
图7 查询学生信息结果图

查看教师信息页面、添加教师信息页面、查询教师信息页面、查看班级信息页面、添加班级信息页面、查询班级信息等页面显示风格、实现细节均与学生相应信息页面类似，不再一一赘述。
其中查看教师信息页面、添加教师信息页面、查询教师信息页面，前端页面实现了而后端服务器处理未去实现，因为其与学生相应页面类似（均与班级存在联系（约束）关系）。

班级与学生间联系（约束）关系：
	修改、添加学生信息点击班级下拉框时，会从数据库中查询当前所有班级的名称，建立起学生班级信息约束与班级名称进行联系。
	添加、删除学生信息，从数据库中添加和删除学生信息的同时，会相应的修改班级表中的班级人数信息（加一或减一）。
	实际数据库学生和教师表中储存的仅仅是班级的班级号码。在实现以上操作时都会从数据库中根据班级名称查询出班级号码，再进一步实施后一步的操作。
	修改学生班级信息时，会先根据学生学号在数据库中查询出其原班级的班号，然后原班级数量减一，修改后的班级数量加一。
	当删除班级信息时，若班级人数不为零，则不能进行删除班级信息操作，服务端反馈给前端删除失败信息“该班级学生人数目前不为0，不能删除”由客户端浏览器弹框显示。若班级人数为零，则从数据库中删除相应班级信息。
 
 ![chanPass](https://raw.githubusercontent.com/GitHub-anonymousV/Gallery/master/GitHub-Imgs/chanPass.4euetaj6itu0.png)
图8修改密码页面

修改密码功能：
	输入新密码，点击提交，会在数据库中修改相应用户（管理员）的密码（根据用户名修改，此处使用MD5对密码进行加密（登录验证时同样也会使用MD5加密后进行比较验证））。

退出登录功能：
	点击退出登录按钮，会弹出确认退出模态框，如图9
 
 ![loginOut](https://raw.githubusercontent.com/GitHub-anonymousV/Gallery/master/GitHub-Imgs/loginOut.65qhqo93o2w0.png)
图9退出登录页面

点击是，会从服务端移除Session储存的登录标志，并重定向到登录页面。点击否，会返回原界面。

有关持久层使用的简化版ORM-Mybatis详细信息参见: https://github.com/Feyl/orm-mybatis
