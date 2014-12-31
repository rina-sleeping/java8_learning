// Run as jjs < pipe.js


function pipe(){
	if(arguments.length < 2){
		exit(1)
	}

	var pb = new java.lang.ProcessBuilder(java.util.Arrays.asList(arguments[0].split(/\s/)))
	var p = pb.start()
	
	for(var i=1;i<arguments.length;i++){
		var next = new java.lang.ProcessBuilder(java.util.Arrays.asList(arguments[i].split(/\s/)))
		
		var pNext = next.start()
		
		//closure use reference, not copy.
		var p1 = p
		var p2 = pNext
		new java.lang.Thread(function() pipeReadWrite(p1, p2)).start()
		
		p = pNext
				
	}
	

	
	var br = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()))
	var output = new java.lang.StringBuilder()
	
	var line
	while((line=br.readLine())!=null){
		output.append(line)
		output.append("\n")
	}
	br.close()
	
	return output.toString()
}

function pipeReadWrite(p, pNext){
	var br = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()))
	var bw = new java.io.BufferedWriter(new java.io.OutputStreamWriter(pNext.getOutputStream()))
	var line
	while((line=br.readLine())!=null){
		bw.write(line)
		bw.newLine()
	}
	br.close()
	bw.close()
}

//test
//pipe('find .', 'grep -v class', 'sort')

var result = pipe('find "a" sample.txt', 'sort', 'find "OK"')

if(result!="aaaaaaaaaa:OK\ndsfaa:OK\n"){
	print("NG1")
}else{
	print("OK1")
}


pipe()

print("NG2")

pipe('find "a" sample.txt')

print("NG3")

