// Run as jjs < sortDisplay.js

print("input target file path")

var path = java.lang.System.console().readLine('File path:')
var contents
try{
	contents = new java.lang.String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(path)),java.nio.charset.StandardCharsets.UTF_8)
}catch(e){
	print("No found:"+path)
	exit(1)
}

var stream = java.util.Arrays.asList(contents.split(/[^\w]/)).stream()


var count = 0
while (count<=12){
	count = java.lang.System.console().readLine('target minimum word length(>12):')
}

var words = stream.filter(function(s){ return s.length() >= count}).sorted().toArray()

var prev
for each(var w in words){
	if(prev!= w){
		print(w)
	}
	prev = w
}

