// Run as jjs -scripting < ex08.js

var env = $ENV

for (var key in env){
	print(key,"=",env[key])
}

