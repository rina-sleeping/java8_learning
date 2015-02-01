package ch8.ex13;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes({ "ch8.ex13.TestCase", "ch8.ex13.TestCases" })
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TestCaseProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		List<Element> list = new LinkedList<Element>();
		for (TypeElement t : annotations) {
			for (Element e : roundEnv.getElementsAnnotatedWith(t)) {
				list.add(e);
			}
		}

		try {
			if (list.size() > 0) {
				writeTestRunner(list);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Writes the source file for the TestRunner class.
	 *
	 * @param beanClassName
	 *            the name of the bean class
	 * @param list
	 *            a list of test target Element
	 */
	private void writeTestRunner(List<Element> list) throws IOException {
		JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile(
				"ch8.ex13.TestRunner");
		PrintWriter out = new PrintWriter(sourceFile.openWriter());

		out.println("package ch8.ex13;\n");
		out.println("import java.lang.reflect.Method;");
		out.println("import java.util.LinkedHashMap;");
		out.println("import java.util.Map;");
		out.println();

		out.println("class TestTarget{");
		out.println("    public String className;");
		out.println("    public String funcName;");
		out.println("    public String detailFuncName;");
		out.println("    TestTarget(String className, String funcName, String detailFuncName){");
		out.println("        this.className = className;");
		out.println("        this.funcName = funcName;");
		out.println("        this.detailFuncName = detailFuncName;");
		out.println("    }");
		out.println("};");

		out.println("class TestCaseInfo{");
		out.println("    public String params;");
		out.println("    public String expected;");
		out.println("    TestCaseInfo(String params, String expected){");
		out.println("        this.params = params;");
		out.println("        this.expected = expected;");
		out.println("    }");
		out.println("};");

		out.println("public class TestRunner{");

		out.println("   public static void main(String[] args){");
		out.println("    ClassLoader loader = ClassLoader.getSystemClassLoader()   ;");

		out.println("    Map<TestTarget, TestCaseInfo[]> tcMap = new LinkedHashMap<>();");

		for (Element e : list) {
			out.print("    TestCaseInfo[] tc = {");
			for (TestCase tc : e.getAnnotationsByType(TestCase.class)) {
				out.print("new TestCaseInfo(\"" + tc.params() + "\",\""
						+ tc.expected() + "\"),");
			}
			out.println("};");
			out.println("    tcMap.put(new TestTarget("
					+ String.join(",", "\""
							+ e.getEnclosingElement().toString() + "\"", "\""
							+ e.getSimpleName().toString() + "\"",
							"\"" + e.toString() + "\"") + "), tc);");
		}
		out.println("    try{");
		out.println("        for(Map.Entry<TestTarget,TestCaseInfo[]> e:tcMap.entrySet()){");
		out.println("            System.out.println(\"===RUN:\"+e.getKey().detailFuncName);");
		out.println("            Class<?> c = loader.loadClass(e.getKey().className);");
		out.println("            Method method = c.getMethod(e.getKey().funcName, new Class[] { int.class });");
		out.println("            boolean pass = true;");

		out.println("            for(TestCaseInfo info: e.getValue()){");
		out.println("                Object ret = method.invoke(c.newInstance(),new Object[] { Integer.valueOf(info.params) });");
		out.println("                String testInfo = \"(params=\" + info.params + \", expected=\" +info.expected +\")\";");
		out.println("                if (ret.toString().equals(info.expected)) {");
		out.println("                    System.out.println(\"OK:\"+testInfo);");
		out.println("                }else{");
		out.println("                    System.out.println(\"NG: (\" + testInfo);");
		out.println("                    System.out.println(ret + \"is returned\");");
		out.println("                    pass = false;");
		out.println("                }");
		out.println("            }");

		out.println("            System.out.println(\"===\"+(pass?\"PASS:\":\"FAIL:\")+e.getKey().detailFuncName);");
		out.println("        }");
		out.println("    } catch (ReflectiveOperationException e) {");
		out.println("        e.printStackTrace();");
		out.println("    }");
		out.println("}");

		out.println("}");
		out.close();
	}
}