package ch2.ex07;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class Ex7 {
	// 終端処理でストリームが終了しないように、フィールド操作からサイズを得なければならない。
	// リフレクションを使用しているため上位の実装が変更になると動かない可能性が高い。
	// またこの関数自体の使い道が分からない。
	// 自分でStreamを作った場合には無限かどうかは分かっているはずだし、引数で受け取った場合には無限かどうかは気にしないで処理をすべき。
	// 無限で終わらない処理になってしまう場合には、その関数の呼び出し元が、関数で行う処理を考えれば無限を引数にしてよいかが分かっているはず。
	public static <T> boolean isFinite(Stream<T> stream) {
		try {
			Field f = stream.getClass().getSuperclass().getSuperclass()
					.getDeclaredField("sourceSpliterator");
			f.setAccessible(true);
			Object splite = f.get(stream);

			Method mSize = splite.getClass().getMethod("getExactSizeIfKnown");
			mSize.setAccessible(true);
			Long size = (Long) mSize.invoke(splite);

			return size != -1;
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		return false;
	}
}
