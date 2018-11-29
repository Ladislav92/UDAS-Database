package ba.rs.udas.database.util;

import static ba.rs.udas.database.util.Utils.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.Optional;

public class Utils {

  public static final class Preconditions {

    private Preconditions() {
    }

    public static <T> T checkNotNull(T reference, String argumentName) {
      if (reference == null) {
        throw new NullPointerException(argumentName + " must not be null");
      }
      return reference;
    }

    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object... args) {
      if (reference == null) {
        throw new NullPointerException(Utils.Formatting.formatString(errorMessageTemplate, args));
      }
      return reference;
    }
  }

  public static final class Reflections {

    private Reflections() {
    }

    public static <T> Optional<T> getField(Class<?> objectType, Class<T> fieldType,
                                           String fieldName, Object targetInstance) {
      checkNotNull(objectType, "objectType");
      checkNotNull(fieldType, "fieldType");
      checkNotNull(fieldName, "fieldName");
      checkNotNull(targetInstance, "targetInstance");

      T value;

      try {
        Field field = objectType.getDeclaredField(fieldName);
        field.setAccessible(true);
        value = (T) field.get(targetInstance);

      } catch (NoSuchFieldException | IllegalAccessException e) {
        return Optional.empty();
      }

      return Optional.of(value);
    }
  }

  public static final class Formatting {

    private Formatting() {
    }

    /**
     * Substitutes each {@code %s} in {@code template} with an argument. These are matched by position: the
     * first {@code %s} gets {@code args[0]}, etc. If there are more arguments than placeholders, the
     * unmatched arguments will be appended to the end of the formatted message in square braces.
     *
     * @param template a string containing 0 or more {@code %s} placeholders. null is treated as "null".
     * @param args the arguments to be substituted into the message template. Arguments are converted to
     * strings using {@link String#valueOf(Object)}. Arguments can be null.
     */
    public static String formatString(String template, Object... args) {
      template = String.valueOf(template); // null -> "null"

      args = args == null ? new Object[]{"(Object[])null"} : args;

      StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
      int templateStart = 0;
      int i = 0;
      while (i < args.length) {
        int placeholderStart = template.indexOf("%s", templateStart);
        if (placeholderStart == -1) {
          break;
        }
        builder.append(template, templateStart, placeholderStart);
        builder.append(args[i++]);
        templateStart = placeholderStart + 2;
      }
      builder.append(template, templateStart, template.length());

      if (i < args.length) {
        builder.append(" [");
        builder.append(args[i++]);
        while (i < args.length) {
          builder.append(", ");
          builder.append(args[i++]);
        }
        builder.append(']');
      }

      return builder.toString();
    }
  }
}
