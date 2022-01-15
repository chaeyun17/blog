package coop.parti.chaeyun17.blog.common.domain.search;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import com.querydsl.core.types.Constant;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SearchBooleanExpUtil {

  public static BooleanExpression getBooleanExpression(EntityPathBase<?> entityPathBase, SearchCondition condition) {
    return SearchBooleanExpUtil.getBooleanExpression(entityPathBase, condition.getName(), condition.getOperation(),
      condition.getValue());
  }

  public static BooleanExpression getBooleanExpression(EntityPathBase<?> entityPathBase, String name,
    String operationChar, Object value) {

    Field field;

    String opMethodName = "";
    switch (operationChar) {
      case "~":
        opMethodName = "containsIgnoreCase";
        break;
      case ":":
        opMethodName = "eq";
        break;
      case ">":
        opMethodName = "gt";
        break;
      case "<":
        opMethodName = "lt";
        break;
    }

    try {
      field = entityPathBase.getClass().getDeclaredField(name);
      Type type = field.getType();
      if (type.equals(StringPath.class)) {
        if (opMethodName.equalsIgnoreCase("eq")) {
          StringPath stringPath = (StringPath)field.get(entityPathBase);
          return (BooleanExpression)stringPath.getClass()
            .getMethod(opMethodName, Object.class)
            .invoke(stringPath, getValueByString(value));
        } else if (opMethodName.equalsIgnoreCase("containsIgnoreCase")) {
          StringPath stringPath = (StringPath)field.get(entityPathBase);
          return (BooleanExpression)stringPath.getClass()
            .getMethod(opMethodName, String.class)
            .invoke(stringPath, getValueByString(value));
        }
      } else if (type.equals(NumberPath.class)) {
        NumberPath<?> numberPath = (NumberPath<?>)field.get(entityPathBase);
        Constant<?> constant = ConstantImpl.create(getValueByLong(value));
        return (BooleanExpression)numberPath.getClass()
          .getMethod(opMethodName, Expression.class)
          .invoke(numberPath, constant);
      } else if (type.equals(DateTimePath.class)) {
        DateTimePath<?> dateTimePath = (DateTimePath<?>)field.get(entityPathBase);
        Constant<?> constant = ConstantImpl.create(getValueByLocalDateTime(value));
        return (BooleanExpression)dateTimePath.getClass()
          .getMethod(opMethodName, Expression.class)
          .invoke(dateTimePath, constant);
      } else if (type.equals(BooleanPath.class)) {
        BooleanPath booleanPath = (BooleanPath)field.get(entityPathBase);
        Constant<?> constant = ConstantImpl.create(getValueByBoolean(value));
        return (BooleanExpression)booleanPath.getClass()
          .getMethod(opMethodName, Expression.class)
          .invoke(booleanPath, constant);
      }

    } catch (NoSuchFieldException |
      IllegalAccessException |
      NoSuchMethodException |
      InvocationTargetException e) {

      log.error(e.getMessage(), e);

    }
    return null;
  }

  public static BooleanExpression[] toArray(List<BooleanExpression> booleanExpressionList) {
    return booleanExpressionList.toArray(new BooleanExpression[0]);
  }

  private static Long getValueByLong(Object value) {
    return Long.valueOf((String)value);
  }

  private static Integer getValueByInteger(Object value) {
    return Integer.valueOf((String)value);
  }

  private static String getValueByString(Object value) {
    return (String)value;
  }

  private static LocalDateTime getValueByLocalDateTime(Object value) {
    long valueLong = Long.parseLong((String)value);
    return LocalDateTime.ofInstant(
      Instant.ofEpochSecond(valueLong, 0),
      ZoneId.of("Asia/Seoul"));
  }

  private static Boolean getValueByBoolean(Object value) {
    return Boolean.valueOf((String)value);
  }

}
