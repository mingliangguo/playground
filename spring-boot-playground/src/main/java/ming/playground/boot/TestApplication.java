package ming.playground.boot;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.core.convert.converter.Converter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestApplication {
  public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
    final String packageName = "ming.playground.boot.converters";
//    scanClassesUsingReflections(packageName);
    scanClassesInPackage(packageName);
  }

  private static void scanClassesUsingReflections(String packageName) throws InstantiationException, IllegalAccessException, InvocationTargetException {
    Reflections reflections = new Reflections(packageName, new SubTypesScanner());
    Set<Class<? extends Converter>> classes = reflections.getSubTypesOf(Converter.class);
    List<Converter> converterList = new ArrayList<>();
    for (Class<? extends Converter>  clazz: classes) {
      String name = clazz.getSimpleName();
      Constructor<?>[] constructors = clazz.getDeclaredConstructors();
      for (Constructor<?>  ctor: constructors) {
        Parameter[] params = ctor.getParameters();
        if (params.length == 0) {
          converterList.add((Converter) ctor.newInstance());
        } else if (params.length == 1 && params[0].getType().equals(SimpleDateFormat.class)) {
          try {
            converterList.add((Converter) ctor.newInstance(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")));
          } catch (Throwable e) {
            e.printStackTrace();
          }
        }
      }
      System.out.println("Converter class name is: " + name);
    }
    System.out.println("all converters:  " + converterList);
  }

  private static void scanClassesInPackage(String packageName) {
    List<String> classNames = new ArrayList<>();
    try (ScanResult scanResult = new ClassGraph().acceptPackages(packageName)
      .enableClassInfo().scan()) {
      ClassInfoList classInfoList = scanResult.getAllClasses();
      for (ClassInfo info: classInfoList) {
        if (info.implementsInterface(Converter.class.getName())) {
          System.out.println("Class: " + info.getName() + " extends Converter!");
        } else {
          System.out.println("Class: " + info.getName() + " does not extend Converter!");
        }
      }
      classNames.addAll(classInfoList.getNames());
    }
  }
}
