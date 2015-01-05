// Run as jjs -fx < ex10.js

if(arguments.length!=1){
	print("usage: jjs nextYear.js -- [data file]")
	exit(1)
}

var path = arguments[0]
var contents
try{
	contents = new java.lang.String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(path)),java.nio.charset.StandardCharsets.UTF_8)
}catch(e){
	print("No found:"+path)
	exit(1)
}

var lines = contents.split(/[\n]/)

var data = javafx.collections.FXCollections.observableArrayList()
for each(var l in lines){
	if(l==""){
		continue
	}
	var d = l.split(/[,]/)
	data.addAll(new javafx.scene.chart.PieChart.Data(d[0], d[1]))
}


var chart = new javafx.scene.chart.PieChart(data)

$STAGE.scene = new javafx.scene.Scene(chart)

//Java‚ÅŠJ”­‚·‚é‚Ì‚Æ‚ ‚Ü‚è•Ï‚í‚ç‚È‚¢

