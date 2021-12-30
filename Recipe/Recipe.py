from flask import Flask, render_template, request, redirect
from DBConnection import Db

app = Flask(__name__)

@app.route('/')
def login():
    return render_template("login.html")

@app.route('/index')
def index():
    return render_template("index.html")

@app.route('/login1',methods=['post'])
def login1():
    name=request.form['txt2']
    password=request.form['txt3']
    qry="select * from login where Username='"+name+"' and Password='"+password+"'"
    ob=Db()
    res = ob.selectOne(qry)
    # print (res)
    if res is not None:
        type = res['type']
        if type == 'admin':
            return redirect('/admin_home')
        else:
            return ''' <script>alert('Invalid');window.location='/'</script> '''
    else:
        return ''' <script>alert('Invalid');window.location='/'</script> '''


@app.route('/admin_home')
def admin_home():
    return render_template("index.html")


@app.route('/admin_view_recipe')
def admin_view_recipe():
    db=Db()
    query="select * from user "
    res=db.select(query)
    return render_template("admin_view_recipe.html",data=res)

@app.route('/recipe/<a>')
def recipe(a):
    db = Db()
    query = "select * from recipe where user_id='"+a+"' "
    res = db.select(query)
    return render_template("recipe_table.html",data=res)

# ----------------recipe----------------

@app.route('/manage_recipe_add')
def manage_recipe_add():
    return render_template("manage_recipe_add.html")

@app.route('/manage_recipe_add1',methods=['post'])
def manage_recipe_add1():
    name=request.form['textfield']
    desc=request.form['textarea']
    image=request.files['fileField']
    # C:\Users\ToShibA\PycharmProjects\Recipe\static\image
    import time
    fname = time.strftime("%Y%m%d_%H%M%S") + ".jpg"
    image.save(r"C:\Users\ToShibA\PycharmProjects\Recipe\static\recipe\\" + fname)

    db=Db()
    query="insert into recipe values (NULL,'"+name+"','"+fname+"','"+desc+"','0')"
    res=db.insert(query)
    return manage_recipe_add()


@app.route('/manage_recipe_view')
def manage_recipe_view():
    db=Db()
    query="select * from recipe"

    res=db.select(query)
    print(res)
    return render_template("manage_recipe_view.html",data=res)


@app.route('/manage_recipe_edit')
def manage_recipe_edit():
    id=request.args.get('id')
    query = "select * from recipe where RecipeID='"+str(id)+"'"
    db=Db()
    res = db.selectOne(query)
    return render_template("manage_recipe_edit.html",data=res)

@app.route('/manage_recipe_edit1',methods=['post'])
def manage_recipe_edit1():
    recipeid=request.form['textfield']
    recipename=request.form['textfield1']
    desc=request.form['textarea']
    image = request.files['filefield1']
    import time
    fname = time.strftime("%Y%m%d_%H%M%S") + ".jpg"
    image.save(r"C:\Users\ToShibA\PycharmProjects\Recipe\static\recipe\\" + fname)

    db=Db()
    query = "update recipe set RecipeName='" + recipename + "',Description='" + desc + "',Image='"+fname+"' where RecipeID='" + recipeid + "'"
    res=db.update(query)
    return manage_recipe_view()


@app.route('/manage_recipe_delete')
def manage_recipe_delete():
    id=request.args.get('id')
    db=Db()
    query="delete from recipe where RecipeID='"+str(id)+"'"
    res=db.delete(query)
    return manage_recipe_view()



 # --------------------vegitable-----------------------


@app.route('/manage_veg_add')
def manage_veg_add():
    return render_template("manage_veg_add.html")


@app.route('/manage_veg_add1',methods=['post'])
def manage_veg_add1():
    name=request.form['textfield']
    desc=request.form['textarea']
    category=request.form['select']

    image=request.files['fileField']
    # C:\Users\ToShibA\PycharmProjects\Recipe\static\image
    
    import time
    fname = time.strftime("%Y%m%d_%H%M%S") + ".jpg"
    image.save(r"C:\Users\ToShibA\PycharmProjects\Recipe\static\image\\" + fname)

    db=Db()
    query="insert into veg values (NULL,'"+name+"','"+fname+"','"+desc+"','"+category+"')"
    res=db.insert(query)
    return manage_veg_add()


@app.route('/manage_veg_view')
def manage_veg_view():
    db=Db()
    query="select * from veg"
    # print(query)
    res=db.select(query)
    # print(res)
    return render_template("manage_veg_view.html",data=res)


@app.route('/manage_veg_edit')
def manage_veg_edit():
    id = request.args.get('id')
    # VegID
    query = "select * from veg where VegID='" + str(id) + "'"
    db = Db()
    res = db.selectOne(query)
    # print(res)
    return render_template("manage_veg_edit.html",data=res)

@app.route('/manage_veg_edit1',methods=['post'])
def manage_veg_edit1():
    vegid=request.form['textfield']
    vegname=request.form['textfield1']
    desc=request.form['textarea']
    category = request.form['select']
    image = request.files['fileField']
    import time
    fname = time.strftime("%Y%m%d_%H%M%S") + ".jpg"
    image.save(r"C:\Users\ToShibA\PycharmProjects\Recipe\static\image\\" + fname)

    db=Db()
    query="update veg set image='"+fname+"',VegName='"+vegname+"',Description='"+desc+"',Category='"+category+"' where VegID='"+vegid+"'"
    res=db.update(query)
    return manage_veg_view()


@app.route('/manage_veg_delete')
def manage_veg_delete():
    id=request.args.get('id')
    db=Db()
    query="delete from veg where VegID='"+str(id)+"'"
    res=db.delete(query)
    return manage_veg_view()




#---------------- Ingredients-----------------



@app.route('/manage_ingredients_add')
def manage_ingredients_add():
    db=Db()
    qry="select * from veg"
    res=db.select(qry)
    print(res)
    qry1="select * from recipe"
    res1=db.select(qry1)
    # print(res1)
    return render_template("manage_ingredients_add.html",data=res,data1=res1)


@app.route('/manage_ingredients_add1',methods=['post'])
def manage_ingredients_add1():
    recipe=request.form['select']
    veg = request.form['select1']
    qnty=request.form['textfield2']
    db=Db()
    query=""
    res=db.insert(query)
    return manage_ingredients_add()


@app.route('/manage_ingredients_view')
def manage_ingredients_view():
    db = Db()
    qry="select * from recipe"
    res=db.select(qry)
    query = "select * from ingredients,veg,recipe where ingredients.VegID=veg.VegID and ingredients.RecipeID=recipe.RecipeID "
    res1 = db.select(query)
    return render_template("manage_ingredients_view.html",data=res,data1=res1)


@app.route('/manage_ingredients_view1',methods=['post'])
def manage_ingredients_view1():
    db = Db()
    recipe=request.form['select1']
    qry="select * from recipe"
    res=db.select(qry)
    query = "select * from ingredients,veg,recipe where ingredients.VegID=veg.VegID and ingredients.RecipeID=recipe.RecipeID and ingredients.RecipeID='"+recipe+"'"
    res1 = db.select(query)
    return render_template("manage_ingredients_view.html",data=res,data1=res1)


@app.route('/manage_ingredients_edit')
def manage_ingredients_edit():
    id = request.args.get('id')
    # RecipeID
    db=Db()
    qry = "select * from veg"
    res = db.select(qry)
    print(res)
    qry1 = "select * from recipe"
    res1 = db.select(qry1)
    query = "select * from ingredients,veg,recipe where ingredients.VegID=veg.VegID and ingredients.RecipeID=recipe.RecipeID and ingredients.IngrID='"+str(id)+"'"
    res2 = db.selectOne(query)
    return render_template("manage_ingredients_edit.html",data=res,data1=res1,data2=res2)


@app.route('/manage_ingredients_edit1/<a>',methods=['post'])
def manage_ingredients_edit1(a):
    recipe = request.form['select']
    veg=request.form['select1']
    qnty=request.form['textfield2']
    db=Db()
    query="update ingredients set VegID='"+veg+"',RecipeID='"+recipe+"',Quantity='"+qnty+"' where IngrID='"+a+"'"
    res=db.update(query)
    return manage_ingredients_view()


@app.route('/manage_ingredients_delete')
def manage_ingredients_delete():
    id=request.args.get('id')
    db=Db()
    query="delete from ingredients where IngrID='"+str(id)+"'"
    res=db.delete(query)
    return manage_ingredients_view()


# ------------------------------------------------------------------------

@app.route('/view_feedback')
def view_feedback():
    db=Db()
    query="select * from feedback,user where feedback.LID=user.user_id"
    res=db.select(query)
    return render_template("view_feedback.html",data=res)


@app.route('/view_rating')
def view_rating():
    db=Db()
    query="select * from rating,user where rating.userid=user.user_id"
    res=db.select(query)
    return render_template("view_rating.html",data=res)

if __name__ == '__main__':
    app.run(debug=True)
