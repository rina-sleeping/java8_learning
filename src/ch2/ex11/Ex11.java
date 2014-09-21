package ch2.ex11;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Ex11 {
	public static <T> ArrayList<T> collect(Stream<T> stream) {
		return stream.parallel().collect(ArrayList::new, ArrayList::add,
				ArrayList::addAll);
	}

	public static <T> ArrayList<T> collect1(Stream<T> stream) {
		long size = 0;
		try {
			Field f = stream.getClass().getSuperclass().getSuperclass()
					.getDeclaredField("sourceSpliterator");
			f.setAccessible(true);
			Object splite = f.get(stream);

			Method mSize = splite.getClass().getMethod("getExactSizeIfKnown");
			mSize.setAccessible(true);
			size = (long) mSize.invoke(splite);

		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		if (size == -1) {
			return new ArrayList<T>();
		}

		ArrayList<T> result = new ArrayList<T>((int) size);

		AtomicInteger id = new AtomicInteger(0);
		stream.parallel().forEach(s -> {
			// �v�f�̏��Ԃ͒����̎��ƕς���Ă��܂��B
				int i = id.getAndIncrement();
				// ArrayList�̓T�C�Y����capacity�͎��邪�A���g����̃T�C�Y0�Ȃ̂ŁA
				// add���Ă���łȂ���set�ł��Ȃ��̂�add�𗘗p
				result.add(i, s);// ���ǂ�result.add(s)�Ɠ���
			});

		return result;
	}
}
