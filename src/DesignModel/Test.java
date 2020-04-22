package DesignModel;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String str = new StringBuffer("test").toString();
        if(str == null){
            char index1 = str.charAt(1);
        }else{

        }
    }
}
;
//观察者接口
interface Observer {
    //由被观察者调用观察者，通知观察者状态修改
    void update(float... params);
}
//收到状态改变通知后响应接口
interface DisplayElement {
    //观察者收到通知后具体响应操作
    void display();
}
//被观察者接口
interface Subject {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObserver();
}

class WeatherData implements Subject {
    private ArrayList<Observer> observers;

    //观察者的容器
    private float[] container;

    private transient int default_size = 1 << 10;

    public WeatherData() {
        observers = new ArrayList<Observer>();
    }

    public WeatherData(List<Observer> observers) {
        if (observers != null && observers.size() >= 0) {
            this.observers = (ArrayList<Observer>) observers;
        } else {
            throw new NullPointerException();
        }
    }

    //向被观察者注册
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    //撤销注册
    @Override
    public void removeObserver(Observer o) {
        int index = observers.indexOf(o);
        if (index >= 0) {
            observers.remove(index);
        }
    }

    //将状态告诉每一个观察者
    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(container);
        }
    }

    public void measurementsChanged() {
        this.notifyObserver();
    }

    public void setMeasurements(float... params) {
        setParams(params);
        measurementsChanged();
    }

    public void setParams(float... params) {
        int length = params.length;
        if (length <= 0) {
            throw new NullPointerException();
        } else if (length < default_size) {
            params = new float[default_size];
        } else {
            default_size = length;
            params = new float[default_size];
        }
        System.arraycopy(params, 0, this.container, 0, length);
    }
}

class CurrentConditionsDisplay implements Observer,DisplayElement{

    private float[] params;

    private transient int default_size = 1 << 10;

    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData){
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float... params) {
        setParams(params);
        display();
    }

    public void setParams(float... params) {
        int length = params.length;
        if (length <= 0) {
            throw new NullPointerException();
        } else if (length < default_size) {
            params = new float[default_size];
        } else {
            default_size = length;
            params = new float[default_size];
        }
        System.arraycopy(params, 0, params, 0, length);
    }

    @Override
    public void display() {
        //do something
    }
}
