package ch2.ex07;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class Ex7 {
	// �I�[�����ŃX�g���[�����I�����Ȃ��悤�ɁA�t�B�[���h���삩��T�C�Y�𓾂Ȃ���΂Ȃ�Ȃ��B
	// ���t���N�V�������g�p���Ă��邽�ߏ�ʂ̎������ύX�ɂȂ�Ɠ����Ȃ��\���������B
	// �܂����̊֐����̂̎g������������Ȃ��B
	// ������Stream��������ꍇ�ɂ͖������ǂ����͕������Ă���͂������A�����Ŏ󂯎�����ꍇ�ɂ͖������ǂ����͋C�ɂ��Ȃ��ŏ��������ׂ��B
	// �����ŏI���Ȃ������ɂȂ��Ă��܂��ꍇ�ɂ́A���̊֐��̌Ăяo�������A�֐��ōs���������l����Ζ����������ɂ��Ă悢�����������Ă���͂��B
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
