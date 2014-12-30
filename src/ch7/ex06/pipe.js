// Run as jjs -scipting < pipe.js


function pipe(){
	if(arguments.length < 2){
		exit(1)
	}
	
	var output = `${arguments[0]}`
	for(var i=1;i<arguments.length;i++){
		var next = arguments[i]
		output = $EXEC(next, output)
	}
	
	return output
}


//test
//pipe('find .', 'grep -v class', 'sort')

var result = pipe('find "a" sample.txt', 'sort', 'find "OK"')

var output = $EXEC("sort", `find "a" sample.txt`)
var expected = $EXEC('find "OK"', output)

if(result!=expected){
	print("NG1")
}else{
	print("OK1")
}


pipe()

print("NG2")

pipe('find "a" sample.txt')

print("NG3")

