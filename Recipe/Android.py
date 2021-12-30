from flask import Flask,request
# from pip._vendor.requests.packages.urllib3 import request
import demjson

from DBConnection import Db
app = Flask(__name__)
db=Db()

@app.route('/login',methods=['post'])
def login():
    db=Db()
    usr=request.form['username']
    passwd=request.form['passwd']
    qry="select * from login where Username='"+usr+"'and Password='"+passwd+"'"
    res=db.selectOne(qry)
    res1={}
    if res!=None:
        type=res['type']
        # print(type)
        if type=="user":
            id=res['Loginid']
            res1['status']='ok'
            res1['id1']=id
            return demjson.encode(res1)
    else:
        res1['status'] = 'none'
        return demjson.encode(res1)

@app.route('/register',methods=['post'])
def register():
    db=Db()
    name=request.form['name']
    user=request.form['username']
    dob=request.form['dob']
    phone=request.form['phone']
    gender=request.form['gender']
    passwd=request.form['pswd']
    cpasswd = request.form['cpswd']
    bio=request.form['bio']
    image=request.files['pic']
    import time
    fname = time.strftime("%Y%m%d_%H%M%S")

    image.save(r"C:\Users\ToShibA\PycharmProjects\Recipe\static\user\\" + fname)
    path = "/static/user/" + fname + ".jpg"

    res1 = {}
    if passwd==cpasswd:

        qry="insert into login values(null,'"+user+"','"+passwd+"','user')"
        res=db.insert(qry)
        qry1="insert into user values ('"+str(res)+"','"+name+"','"+dob+"','"+gender+"','"+phone+"','"+user+"','"+path+"','"+bio+"')"
        res2=db.insert(qry1)
        res1['status']='ok'
        return demjson.encode(res1)

    else:
        res1['status'] = 'Password Mismatch'
        return demjson.encode(res1)


@app.route('/recipe',methods=['post'])
def recipe():
    q1="select * from recipe"
    res=db.select(q1)
    res1 = {}
    res1["status"] = "ok"
    res1["data"] = res
    return demjson.encode(res1)

@app.route('/recipe_search',methods=['post'])
def recipe_search():
    recipe=request.form['recipe']
    q1="select * from recipe where RecipeName like '%"+recipe+"%'"
    res=db.select(q1)
    res1 = {}
    res1["status"] = "ok"
    res1["data"] = res
    return demjson.encode(res1)

@app.route('/vegs',methods=['get'])
def vegs():
    q1="select * from veg"
    res=db.select(q1)
    res1 = {}
    res1["status"] = "ok"
    res1["data"] = res
    return demjson.encode(res1)



@app.route('/profile',methods=['post'])
def profile():
    lid=request.form['login']
    q1="select * from user where user_id='"+str(lid)+"'"
    res=db.selectOne(q1)
    res1 = {}
    res1["status"] = "ok"
    res1["data"] = res
    return demjson.encode(res1)

@app.route('/add_recipe',methods=['post'])
def add_recipe():
    lid=request.form['lid']
    name=request.form['name']
    details=request.form['details']
    image = request.files['pic']
    import time
    fname = time.strftime("%Y%m%d_%H%M%S")

    image.save(r"C:\Users\ToShibA\PycharmProjects\Recipe\static\recipe\\" + fname)
    path="/static/recipe/"+fname+".jpg"

    q1="insert into recipe(RecipeName,image,Description,user_id) values('"+name+"','"+path+"','"+details+"','"+lid+"')"
    res=db.insert(q1)
    res1 = {}
    res1["status"] = "ok"
    return demjson.encode(res1)

@app.route('/feedback',methods=['post'])
def feedback():
    lid=request.form['login']
    details=request.form['details']
    q1="insert into feedback(Feedback,LID,Date) values('"+details+"','"+lid+"',curdate())"
    res=db.insert(q1)
    res1 = {}
    res1["status"] = "ok"
    return demjson.encode(res1)


@app.route('/rating',methods=['post'])
def rating():
    lid=request.form['id']
    rate=request.form['rr']
    q1="insert into rating values(null,'"+rate+"','"+lid+"',curdate())"
    res=db.insert(q1)
    res1 = {}
    res1["status"] = "ok"
    return demjson.encode(res1)




if __name__=="__main__":
    app.run(host="0.0.0.0")