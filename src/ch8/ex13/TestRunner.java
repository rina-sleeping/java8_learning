package ch8.ex13;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

class TestTarget{
    public String className;
    public String funcName;
    public String detailFuncName;
    TestTarget(String className, String funcName, String detailFuncName){
        this.className = className;
        this.funcName = funcName;
        this.detailFuncName = detailFuncName;
    }
};
class TestCaseInfo{
    public String params;
    public String expected;
    TestCaseInfo(String params, String expected){
        this.params = params;
        this.expected = expected;
    }
};
public class TestRunner{
   public static void main(String[] args){
    ClassLoader loader = ClassLoader.getSystemClassLoader()   ;
    Map<TestTarget, TestCaseInfo[]> tcMap = new LinkedHashMap<>();
    TestCaseInfo[] tc = {new TestCaseInfo("4","24"),new TestCaseInfo("0","1"),};
    tcMap.put(new TestTarget("ch8.ex13.TestCaseDemo","factorial","factorial(int)"), tc);
    try{
        for(Map.Entry<TestTarget,TestCaseInfo[]> e:tcMap.entrySet()){
            System.out.println("===RUN:"+e.getKey().detailFuncName);
            Class<?> c = loader.loadClass(e.getKey().className);
            Method method = c.getMethod(e.getKey().funcName, new Class[] { int.class });
            boolean pass = true;
            for(TestCaseInfo info: e.getValue()){
                Object ret = method.invoke(c.newInstance(),new Object[] { Integer.valueOf(info.params) });
                String testInfo = "(params=" + info.params + ", expected=" +info.expected +")";
                if (ret.toString().equals(info.expected)) {
                    System.out.println("OK:"+testInfo);
                }else{
                    System.out.println("NG: (" + testInfo);
                    System.out.println(ret + "is returned");
                    pass = false;
                }
            }
            System.out.println("==="+(pass?"PASS:":"FAIL:")+e.getKey().detailFuncName);
        }
    } catch (ReflectiveOperationException e) {
        e.printStackTrace();
    }
}
}
