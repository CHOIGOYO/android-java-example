package choigoyo.study.android.permissionexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

import choigoyo.study.android.permissionexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    // 확인 받은 모든 권한 목록
    String[] permissionList = {
            Manifest.permission.INTERNET, // 허용 상태이기 때문에 요청할 필요는 없음
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ButtonClickListener1 btnListener1 = new ButtonClickListener1();
        binding.button.setOnClickListener(btnListener1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // 권한 확인이 끝나면 자동으로 호출되는 메서드
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        binding.resultTextView.setText("사용자 확인 요청   \n");
        // 권한 별로 분기하기
        for (int i = 0; i < permissions.length; i++) {
            // 현재 권한의 이름을 가져온다
            String permission = permissions[i];
            // 허용 여부값을 가져온다
            int chk = grantResults[i];
            String permissionNm = "";
            switch (permission) {
                case (Manifest.permission.INTERNET):
                    permissionNm = "인터넷";
                    break;
                case (Manifest.permission.ACCESS_FINE_LOCATION):
                    permissionNm = "위치1";
                    break;
                case (Manifest.permission.ACCESS_COARSE_LOCATION):
                    permissionNm = "위치2";
                    break;
                case (Manifest.permission.READ_CONTACTS):
                    permissionNm = "연락처 읽기";
                    break;
                case (Manifest.permission.WRITE_CONTACTS):
                    permissionNm = "연락처 쓰기";
                    break;
                case (Manifest.permission.READ_EXTERNAL_STORAGE):
                    permissionNm = "저장소 읽기";
                    break;
                case (Manifest.permission.WRITE_EXTERNAL_STORAGE):
                    permissionNm = "저장소 쓰기";
                    break;
            }
            binding.resultTextView.append(getStrPermissionResult(chk == PackageManager.PERMISSION_DENIED, permissionNm));
        }
    }

    String getStrPermissionResult(Boolean chk, String name) {
        return name + " : " + (chk ? "허용" : "거부") + "\n";
    }

    class ButtonClickListener1 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // 거부 되어있는 권한들을 사용자에게 확인한다.
            requestPermissions(permissionList, 0);
        }
    }

    // 버튼에 알맞는 권한을 요청하기 위한 class
    class LocationPermissionCallback implements ActivityResultCallback<Map<String, Boolean>> {
        @Override
        public void onActivityResult(Map<String, Boolean> o) {
        }
    }

    class ContactsPermissionCallback implements ActivityResultCallback<Map<String, Boolean>> {
        @Override
        public void onActivityResult(Map<String, Boolean> o) {

        }
    }

    class ExternalStoragePermissionCallback implements ActivityResultCallback<Map<String, Boolean>> {
        @Override
        public void onActivityResult(Map<String, Boolean> o) {

        }
    }
}