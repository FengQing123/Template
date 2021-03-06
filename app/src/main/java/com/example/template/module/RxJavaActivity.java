package com.example.template.module;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.common.util.L;
import com.example.template.R;
import com.example.template.app.BaseActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * 功能描述：RxJava2.X 学习
 * <p>
 * Created by gfq on 2020/1/20.
 */
@SuppressWarnings("unused")
public class RxJavaActivity extends BaseActivity {

    private static final String TAG = "RxJavaActivity";

    @Override
    protected int getLayout() {
        return R.layout.activity_rxjava;
    }

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        doSubscribeFlowable();


    }

    /**
     * 使用 上游Flowable 和  下游Subscriber 来建立连接
     * <p>
     * 还要注意：
     * BackpressureStrategy.ERROR
     * Subscription
     */
    private void doSubscribeFlowable() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
//                Log.e(TAG, "1");
//                emitter.onNext(1);
//                Log.e(TAG, "2");
//                emitter.onNext(2);
//                Log.e(TAG, "3");
//                emitter.onNext(3);
//                Log.e(TAG, "4");
//                emitter.onNext(4);
//                Log.e(TAG, "emit onComplete");
//                emitter.onComplete();

                Log.e(TAG, "thread1=" + Thread.currentThread().getName());

                for (int i = 0; i < 128; i++) {
                    emitter.onNext(i);
                    Log.e(TAG, "i=" + i);
                }
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.e(TAG, "onSubscribe");
                        Log.e(TAG, "thread2=" + Thread.currentThread().getName());

//                        s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e(TAG, "onNext=" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "onError=" + t);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                });
    }

    /**
     * RxJava 操作符 sample():每隔指定的时间就从上游中取出一个事件发送给下游
     */
    private void doSubscribeSample() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {  //无限循环发送事件
                    emitter.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io())
                .sample(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "" + integer);
                    }
                });
    }

    /**
     * RxJava 操作符 filter():过滤数据
     */
    private void doSubscribeFilter() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {  //无限循环发送事件
                    emitter.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 10 == 0;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "" + integer);
                    }
                });
    }

    /**
     * RxJava 操作符 zip():把多个Observable发送的事件组合到一起，接着按顺序发射
     * 需要注意的是最终发射的数据是和最少的那个Observable数据一样多
     * <p>
     * 用法：
     * 可能某个界面需要两个接口的数据，我们就可以使用了
     */
    private void doSubscribeZip() {
        Observable observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("A");
                Log.e(TAG, "A");
                emitter.onNext("B");
                Log.e(TAG, "B");
                emitter.onNext("C");
                Log.e(TAG, "C");
                emitter.onNext("D");
                Log.e(TAG, "D");
            }
        }).subscribeOn(Schedulers.io());

        Observable observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Log.e(TAG, "1");

                emitter.onNext(2);
                Log.e(TAG, "2");

                emitter.onNext(3);
                Log.e(TAG, "3");

                emitter.onNext(4);
                Log.e(TAG, "4");

                emitter.onNext(5);
                Log.e(TAG, "5");


            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<String, Integer, String>() {

            @Override
            public String apply(String s, Integer integer) throws Exception {
                return s + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "s=" + s);
            }
        });
    }

    /**
     * 实现多个网络请求嵌套方法
     * 这里只是个参考
     */
    private void doSubscribeRegisterLogin() {
//        api.register(new RegisterRequest())            //发起注册请求
//                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
//                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求注册结果
//                .doOnNext(new Consumer<RegisterResponse>() {
//                    @Override
//                    public void accept(RegisterResponse registerResponse) throws Exception {
//                        //先根据注册的响应结果去做一些操作
//                    }
//                })
//                .observeOn(Schedulers.io())                 //回到IO线程去发起登录请求
//                .flatMap(new Function<RegisterResponse, ObservableSource<LoginResponse>>() {
//                    @Override
//                    public ObservableSource<LoginResponse> apply(RegisterResponse registerResponse) throws Exception {
//                        return api.login(new LoginRequest());
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求登录的结果
//                .subscribe(new Consumer<LoginResponse>() {
//                    @Override
//                    public void accept(LoginResponse loginResponse) throws Exception {
//                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
//                    }
//                });
    }

    /**
     * RxJava 的 flatMap()函数：将一个发送事件的上游Observable 变换为 多个发送事件的Observables，然后将它们发射的事件合并后放进一个单独的Observable里
     * flatMap() 不保证有序，如果需要有序可以使用concatMap()
     */
    private void doSubscribeFlatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list).delay(10000, TimeUnit.MICROSECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
    }

    /**
     * RxJava 的map 函数：对上游发送的每一个事件应用一个函数, 使得每一个事件都按照指定的函数去变化
     * 以下的例子就是把发送的 Integer 转为 String
     */
    private void doSubscribeMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "This is result " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "s=" + s);
            }
        });
    }

    /**
     * 实现上游和下游线程控制功能
     * <p>
     * subscribeOn() 指定的是上游发送事件的线程, observeOn() 指定的是下游接收事件的线程
     * 上游的线程只有第一次指定的有效，下游的线程每调用一次observeOn(),就会切换一次
     * <p>
     * RxJava内置的线程（使用线程池来维护线程）：
     * Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
     * Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作
     * Schedulers.newThread() 代表一个常规的新线程
     * AndroidSchedulers.mainThread() 代表Android的主线程
     * <p>
     * 可以配合Retrofit进行网络请求：
     * 在Activity页面消失时，我们可以通过Disposable的dispose()方法来切断请求回调，
     * 可以使用CompositeDisposable.add()添加Disposable,并通过CompositeDisposable.clear()切断所有的请求回调
     */
    private void doSubscribeThree() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
                Log.d(TAG, "onNext: " + integer);
            }
        };

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);

        observable.subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "After observeOn(mainThread), current thread is: " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "After observeOn(io), current thread is : " + Thread.currentThread().getName());
                    }
                })
                .subscribe(consumer);
    }

    /**
     * 只接收onNext()的subscribe()
     * <p>
     * Observable有多个重载subscribe()的方法：
     * public final Disposable subscribe() {} //下游不关心任何事件
     * public final Disposable subscribe(Consumer<? super T> onNext) {} //下游只关心 onNext事件
     * public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError) {}
     * public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete) {}
     * public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete, Consumer<? super Disposable> onSubscribe) {}
     * public final void subscribe(Observer<? super T> observer) {}
     */
    private void doSubscribeTwo() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello");
                emitter.onNext("Android");
                emitter.onComplete();
                emitter.onNext("Java");
                emitter.onNext("PHP");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });
    }

    /**
     * 正常订阅操作
     */
    private void doSubscribeOne() {
        /**
         * 创建Observable 被观察者对象
         */
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello");
                emitter.onNext("Android");
                emitter.onComplete();
                emitter.onNext("Java");
                emitter.onNext("PHP");
            }
        });

        /**
         * 创建Observer 观察者对象
         */
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                L.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                L.e(TAG, "onNext s=" + s);

            }

            @Override
            public void onError(Throwable e) {
                L.e(TAG, "onError=" + e.getMessage());

            }

            @Override
            public void onComplete() {
                L.e(TAG, "onComplete");

            }
        };

        /**
         * 建立连接
         */
        observable.subscribe(observer);
    }


}
