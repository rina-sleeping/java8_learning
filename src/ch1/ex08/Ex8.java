package ch1.ex08;

import java.util.ArrayList;
import java.util.List;

public class Ex8 {

	public static void main(String[] args) {
		String[] names = { "Peter", "Paul", "Mary" };
		List<Runnable> runners = new ArrayList<>();
		for (String name : names)
			runners.add(() -> System.out.println(name));// �����ȃR�[�h�B�قȂ�l���L���v�`������B

		for (Runnable runner : runners)
			runner.run();

		List<Runnable> runners2 = new ArrayList<>();

		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			// ��UString�^�ŕۑ����Ȃ��Ƃ����Ȃ��B�����_�����ł�names[i]�ŎQ�Ƃł��Ȃ��B
			// �uLocal variable i defined in an enclosing scope must be final or
			// effectively final�v�Əo��B
			// System.out.println(i)�����l�ɃG���[���o��Bi�������_���O�ł��ύX����Ă����p.13�̐���ɂ��������Ă���炵��
			runners2.add(() -> System.out.println(name));
		}
		for (Runnable runner : runners2)
			runner.run();
	}

}
