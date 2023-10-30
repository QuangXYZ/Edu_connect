package com.example.edu_connect.Controller;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.Test;
import com.example.edu_connect.Repository.TestRepository;

import java.util.List;

public class TestController {

    public void addTest(Course course,Test test, Callback callback) {
        TestRepository.addTest(course, test, new TestRepository.Callback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);

            }
        });
    }
    public void getTest(String courseId, final GetTestsCallback getTestsCallback){
        TestRepository.getTests(courseId, new TestRepository.GetTestsCallback() {
            @Override
            public void onSuccess(List<Test> tests) {
                getTestsCallback.onSuccess(tests);
            }

            @Override
            public void onFailure(Exception e) {
                getTestsCallback.onFailure(e);

            }
        });

    }
    public interface Callback {
        void onSuccess();
        void onFailure(Exception e);
    }
    public interface GetTestsCallback {
        void onSuccess(List<Test> tests);
        void onFailure(Exception e);
    }


}
