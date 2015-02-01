package ch8.ex12;

import java.lang.reflect.Method;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes({ "ch8.ex12.TestCase", "ch8.ex12.TestCases" })
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TestCaseProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		ClassLoader loader = this.getClass().getClassLoader();
		for (TypeElement t : annotations) {
			for (Element e : roundEnv.getElementsAnnotatedWith(t)) {
				try {
					Class<?> c = loader.loadClass(e.getEnclosingElement()
							.toString());
					Method method = c.getMethod(e.getSimpleName().toString(),
							new Class[] { int.class });

					System.out.println("====RUN:" + e);

					boolean pass = true;
					for (TestCase tc : e.getAnnotationsByType(TestCase.class)) {
						Object ret = method.invoke(c.newInstance(),
								new Object[] { Integer.valueOf(tc.params()) });

						if (ret.toString().equals(tc.expected())) {
							System.out.println("OK: " + tc.toString());
						} else {
							System.out.println("NG: " + tc.toString());
							System.out.println("expected " + tc.expected()
									+ ", but " + ret + " is returned.");
							pass = false;
						}
					}
					System.out.println((pass ? "====OK:" : "====FAIL:") + e);

				} catch (ReflectiveOperationException e1) {
					e1.printStackTrace();
					return false;
				}

			}
		}
		return true;
	}
}
