// Run as jjs < livingDuration.js

//ch6.ex05 in jjs
function livingDuration(b) { return b.until(java.time.LocalDate.now(),java.time.temporal.ChronoUnit.DAYS) }

// test
for (i=0;i<10;i=i+1){
 var days = livingDuration(java.time.LocalDate.now().minusDays(i))
 
 if (days != i){
   print("NG")
   exit(1)
 }
}

print("OK")

//短いからかあまりJavaとの違いを感じなかった。
//テストユーティリティを入れなかったせいで面倒だとは思ったが。


