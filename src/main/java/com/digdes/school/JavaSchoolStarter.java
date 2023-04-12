package com.digdes.school;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaSchoolStarter {


    private ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
    private final List<Map<String, Object>> data = new ArrayList<>();


    public JavaSchoolStarter(ConcurrentHashMap<String, Object> map) {
        this.map = map;
    }

    public JavaSchoolStarter() {


    }

    public List<Map<String, Object>> execute(String request) throws Exception {


        Pattern pattern = Pattern.compile("\\'\\w+[^,. \\d]|\\‘\\w+[^,. \\d]|\\w+[^,. \\d]|\\d+.\\d+|\\d+|\\>[^,. ]|\\<[^,. ]|\\![^,. ]|[^,. ]", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(request);
        List<String> str = new ArrayList<>();
        while (matcher.find()) {
            str.add(matcher.group());
        }
        switch (str.get(0)) {
            case "INSERT": {
                str.removeIf(nextStr -> nextStr.equals("="));
                ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
                for (int i = 2; i < str.size(); i++) {
                    map.put(str.get(i), str.get(i + 1));
                    i++;
                }
                data.add(this.map = map);


                break;

            }
            case "UPDATE": {
                boolean where = !(str.stream().anyMatch(s -> s.equalsIgnoreCase("where")));
                if (where) {
                    str.removeIf(nextStr -> nextStr.equals("="));
                    for (Map<String, Object> datum : data) {
                        for (Map.Entry<String, Object> entry : datum.entrySet()) {
                            for (int j = 2; j < str.size(); j++) {
                                datum.put(str.get(j), str.get(j + 1));
                                j++;
                            }
                        }
                    }
                }

                str.subList(0, str.size() - 2).removeIf(nextStr -> nextStr.equals("="));
                for (int i = 0; i < str.size(); i++) {
                    int indexOfWhere = 0;
                    if (str.get(i).equalsIgnoreCase("where")) {
                        indexOfWhere = i + 1;
                        for (Map<String, Object> datum : data) {
                            for (Map.Entry<String, Object> entry : datum.entrySet()) {
                                switch (str.get(indexOfWhere)) {
                                    case "'id'": {
                                        if (str.get(indexOfWhere).equals(entry.getKey()) && str.get(indexOfWhere + 2).equals(entry.getValue())) {
                                            for (int j = 2; j < indexOfWhere - 1; j++) {
                                                datum.put(str.get(j), str.get(j + 1));
                                                j++;
                                            }
                                        }
                                        break;
                                    }

                                    case "'active'": {
                                        if (str.get(indexOfWhere).equals(entry.getKey()) && (str.get(indexOfWhere + 2).equals(entry.getValue()))) {
                                            for (int j = 2; j < indexOfWhere - 1; j++) {
                                                datum.put(str.get(j), str.get(j + 1));
                                                j++;
                                            }
                                            break;
                                        }
                                    }
                                    case "'age'": {
                                        if (str.get(indexOfWhere).equals(entry.getKey()) && (Integer.parseInt((String) entry.getValue()) >= Integer.parseInt(str.get(str.size() - 1))) && str.contains(">=")) {
                                            for (int j = 2; j < indexOfWhere - 1; j++) {
                                                datum.put(str.get(j), str.get(j + 1));
                                                j++;
                                            }
                                        } else if (str.get(indexOfWhere).equals(entry.getKey()) && (Integer.parseInt((String) entry.getValue()) <= Integer.parseInt(str.get(str.size() - 1))) && str.contains("<=")) {
                                            for (int j = 2; j < indexOfWhere - 1; j++) {
                                                datum.put(str.get(j), str.get(j + 1));
                                                j++;
                                            }
                                        } else if (str.get(indexOfWhere).equals(entry.getKey()) && (Integer.parseInt((String) entry.getValue()) < Integer.parseInt(str.get(str.size() - 1))) && str.contains("<")) {
                                            for (int j = 2; j < indexOfWhere - 1; j++) {
                                                datum.put(str.get(j), str.get(j + 1));
                                                j++;
                                            }
                                        } else if (str.get(indexOfWhere).equals(entry.getKey()) && (Integer.parseInt((String) entry.getValue()) > Integer.parseInt(str.get(str.size() - 1))) && str.contains(">")) {
                                            for (int j = 2; j < indexOfWhere - 1; j++) {
                                                datum.put(str.get(j), str.get(j + 1));
                                                j++;
                                            }
                                        } else if (str.get(indexOfWhere).equals(entry.getKey()) && (Integer.parseInt((String) entry.getValue()) == Integer.parseInt(str.get(str.size() - 1))) && str.contains("=")) {
                                            for (int j = 2; j < indexOfWhere - 1; j++) {
                                                datum.put(str.get(j), str.get(j + 1));
                                                j++;
                                            }
                                        } else if (str.get(indexOfWhere).equals(entry.getKey()) && (Integer.parseInt((String) entry.getValue()) != Integer.parseInt(str.get(str.size() - 1))) && str.contains("!=")) {
                                            for (int j = 2; j < indexOfWhere - 1; j++) {
                                                datum.put(str.get(j), str.get(j + 1));
                                                j++;
                                            }
                                        }
                                        break;
                                    }
                                    case "'cost'": {
                                        if (str.get(indexOfWhere).equals(entry.getKey()) && (Double.parseDouble(String.valueOf(entry.getValue())) >= (Double.parseDouble(String.valueOf(str.get(str.size() - 1)))) && str.contains(">="))) {
                                            for (int j = 2; j < indexOfWhere - 1; j++) {
                                                datum.put(str.get(j), str.get(j + 1));
                                                j++;
                                            }
                                        } else if (str.get(indexOfWhere).equals(entry.getKey()) && (Double.parseDouble(String.valueOf(entry.getValue())) <= (Double.parseDouble(String.valueOf(str.get(str.size() - 1)))) && str.contains("<="))) {
                                            for (int j = 2; j < indexOfWhere - 1; j++) {
                                                datum.put(str.get(j), str.get(j + 1));
                                                j++;
                                            }
                                        } else if (str.get(indexOfWhere).equals(entry.getKey()) && (Double.parseDouble(String.valueOf(entry.getValue())) < (Double.parseDouble(String.valueOf(str.get(str.size() - 1)))) && str.contains("<"))) {
                                            for (int j = 2; j < indexOfWhere - 1; j++) {
                                                datum.put(str.get(j), str.get(j + 1));
                                                j++;
                                            }
                                        } else if (str.get(indexOfWhere).equals(entry.getKey()) && (Double.parseDouble(String.valueOf(entry.getValue())) > (Double.parseDouble(String.valueOf(str.get(str.size() - 1)))) && str.contains(">"))) {
                                            for (int j = 2; j < indexOfWhere - 1; j++) {
                                                datum.put(str.get(j), str.get(j + 1));
                                                j++;
                                            }
                                        } else if (str.get(indexOfWhere).equals(entry.getKey()) && (Double.parseDouble(String.valueOf(entry.getValue())) == (Double.parseDouble(String.valueOf(str.get(str.size() - 1)))) && str.contains("="))) {
                                            for (int j = 2; j < indexOfWhere - 1; j++) {
                                                datum.put(str.get(j), str.get(j + 1));
                                                j++;
                                            }

                                        } else if (str.get(indexOfWhere).equals(entry.getKey()) && (Double.parseDouble(String.valueOf(entry.getValue())) != (Double.parseDouble(String.valueOf(str.get(str.size() - 1)))) && str.contains("!="))) {
                                            for (int j = 2; j < indexOfWhere - 1; j++) {
                                                datum.put(str.get(j), str.get(j + 1));
                                                j++;
                                            }
                                        }
                                        break;
                                    }


                                }

                            }

                        }

                    }
                }

                break;

            }

            case "DELETE": {
                if (str.stream().noneMatch(s -> s.equalsIgnoreCase("where"))) {
                    if (data.size() > 0) {
                        data.subList(0, data.size()).clear();
                    }

                }
                str.subList(0, str.size() - 2).removeIf(nextStr -> nextStr.equals("="));
                for (int i = 0; i < str.size(); i++) {
                    int indexOfWhere = 0;
                    if (str.get(i).equalsIgnoreCase("where")) {
                        indexOfWhere = i + 1;
                        for (Map<String, Object> datum : data) {
                            for (Map.Entry<String, Object> entry : datum.entrySet()) {
                                switch (str.get(indexOfWhere)) {
                                    case "‘id’": {
                                        if (str.get(indexOfWhere).replaceAll("‘|’", "").equals(entry.getKey().replace("'", "")) && str.get(indexOfWhere + 2).equals(entry.getValue())) {
                                            data.remove(datum);
                                        }
                                    }
                                    break;
                                }

                            }

                        }

                    }
                }
                break;
            }
            case "SELECT": {
                if (str.stream().noneMatch(s -> s.equalsIgnoreCase("where"))) {
                    for (Map<String, Object> datum : data) {
                        System.out.println(datum);
                    }

                }
                break;
            }


        }


        return data;
    }
}




