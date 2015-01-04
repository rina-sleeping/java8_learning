// Run as jjs -scripting < nextYear.js

if(arguments.length>1){
	print("usage: jjs nextYear.js -- [your age or nothing]")
	exit(1)
}

var age

if(arguments.length==1){
	age = arguments[0]
}else if($ENV.AGE!=null){
	print($ENV.AGE)
	age = $ENV.AGE
}else{
	age = readLine('Your age: ')
}
var next = Number(age)+1
print("Next year, you will be ${next}")

