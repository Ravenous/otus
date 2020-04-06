import str.Stroka;
import str.StrokaArray;
import str.StrokaStack;

public class Main {

	public static void main(String[] args) {

		String string = "Hello world!";
		Stroka stroka = new Stroka(string);
		
		System.out.println("-------------------------------------------------------");
		System.out.println("- Создали строку символов                             -");
		System.out.println("-------------------------------------------------------");
		System.out.println("Строка из массива байт: " + stroka.toString());
		System.out.println("Длина строки в байтах: " + stroka.getStrokaLenBytes());
		
		System.out.println("-------------------------------------------------------");
		System.out.println("- Очистили строку                                     -");
		System.out.println("-------------------------------------------------------");
		stroka.clearStroka();
		System.out.println("Строка из массива байт: " + stroka.toString());
		System.out.println("Длина строки в байтах: " + stroka.getStrokaLenBytes());
		
		System.out.println("-------------------------------------------------------");
		System.out.println("- Часть строки в массив                               -");
		System.out.println("-------------------------------------------------------");
		StrokaArray strokaArray = new StrokaArray();
		System.out.println("Часть строки из массива символов: " + new String(strokaArray.getCharArray(string, 6)));
		
		System.out.println("-------------------------------------------------------");
		System.out.println("- Создаём стек размером (1)                           -");
		System.out.println("-------------------------------------------------------");
		StrokaStack strStack = new StrokaStack(1);
		System.out.println("Размер стека: " + strStack.getStackSize());
		System.out.println("Стек пустой?: " + strStack.isEmpty());
		
		System.out.println("-------------------------------------------------------");
		System.out.println("- Последовательно добавляем 3 элемента в стек         -");
		System.out.println("-------------------------------------------------------");
		strStack.putStackEl("Brave");
		strStack.putStackEl("New");
		strStack.putStackEl("World!");
		System.out.println("Стек пустой?: " + strStack.isEmpty());
		System.out.println("Размер стека: " + strStack.getStackSize());
		System.out.println("В стеке: " + strStack.toString());
		
		System.out.println("-------------------------------------------------------");
		System.out.println("- Получаем верхний элемент стека                      -");
		System.out.println("-------------------------------------------------------");
		System.out.println("Стек пустой?: " + strStack.isEmpty());
		System.out.println("Размер стека: " + strStack.getStackSize());
		System.out.println("В стеке: " + strStack.toString());
		System.out.println("Верхний элемент: " + new String(strStack.getStackEl().getStrokaBytes()));
		
		System.out.println("-------------------------------------------------------");
		System.out.println("- Удаляем верхний элемент стека                       -");
		System.out.println("-------------------------------------------------------");
		strStack.deleteStackEl();
		System.out.println("Стек пустой?: " + strStack.isEmpty());
		System.out.println("Размер стека: " + strStack.getStackSize());
		System.out.println("В стеке: " + strStack.toString());
		System.out.println("Верхний элемент: " + new String(strStack.getStackEl().getStrokaBytes()));
		
	}

}
