// Run as jjs < sortDisplay.js


function factory(){ 
	var arr = new (Java.extend(java.util.ArrayList)){
		add: function(x){
			print('Adding ' + x);
			return Java.super(arr).add(x)
		}
	}
	return arr
}


//test 
var arr1 = factory()
var arr2 = factory()

arr1.add('Fred')
arr2.add('Alice')
arr2.add('Bob')

if (arr1.size()!=1){
	print("FAIL")
	exit(1)
}

if (arr2.size()!=2){
	print("FAIL")
	exit(1)
}

