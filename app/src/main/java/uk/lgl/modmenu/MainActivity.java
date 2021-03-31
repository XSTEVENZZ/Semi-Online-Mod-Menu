package uk.lgl.modmenu;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.print.PrintAttributes;
import android.text.Html;
import android.text.InputFilter;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.RelativeLayout.ALIGN_PARENT_BOTTOM;
import static android.widget.RelativeLayout.ALIGN_PARENT_LEFT;
import static android.widget.RelativeLayout.ALIGN_PARENT_RIGHT;
import static android.widget.RelativeLayout.CENTER_IN_PARENT;
import static java.lang.System.loadLibrary;

public class MainActivity extends Activity {
    //no meu caso eu iniciei o mod menu mas eu poderia ter iniciado o jogo apenas mudando essa string
    public String sGameActivity = "uk.lgl.modmenu.MainActivity";
    //url para seu canal no youtube - URL YOUTUBE CHANEL
    private native String URL();
    private native String Dialog();
    private native String TeamName();

    //tamanho

    //tamanho
    String base64String2 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEsAAABLCAYAAAA4TnrqAAAABGdBTUEAALGPC/xhBQAACk1pQ0NQUGhvdG9zaG9wIElDQyBwcm9maWxlAAB4nJ1Td1iT9xY+3/dlD1ZC2PCxl2yBACIjrAjIEFmiEJIAYYQQEkDFhYgKVhQVEZxIVcSC1QpInYjioCi4Z0GKiFqLVVw47h/cp7V9eu/t7fvX+7znnOf8znnPD4AREiaR5qJqADlShTw62B+PT0jEyb2AAhVI4AQgEObLwmcFxQAA8AN5eH50sD/8Aa9vAAIAcNUuJBLH4f+DulAmVwAgkQDgIhLnCwGQUgDILlTIFADIGACwU7NkCgCUAABseXxCIgCqDQDs9Ek+BQDYqZPcFwDYohypCACNAQCZKEckAkC7AGBVgVIsAsDCAKCsQCIuBMCuAYBZtjJHAoC9BQB2jliQD0BgAICZQizMACA4AgBDHhPNAyBMA6Aw0r/gqV9whbhIAQDAy5XNl0vSMxS4ldAad/Lw4OIh4sJssUJhFykQZgnkIpyXmyMTSOcDTM4MAAAa+dHB/jg/kOfm5OHmZuds7/TFov5r8G8iPiHx3/68jAIEABBOz+/aX+Xl1gNwxwGwdb9rqVsA2lYAaN/5XTPbCaBaCtB6+Yt5OPxAHp6hUMg8HRwKCwvtJWKhvTDjiz7/M+Fv4It+9vxAHv7bevAAcZpAma3Ao4P9cWFudq5SjufLBEIxbvfnI/7HhX/9jinR4jSxXCwVivFYibhQIk3HeblSkUQhyZXiEul/MvEflv0Jk3cNAKyGT8BOtge1y2zAfu4BAosOWNJ2AEB+8y2MGguRABBnNDJ59wAAk7/5j0ArAQDNl6TjAAC86BhcqJQXTMYIAABEoIEqsEEHDMEUrMAOnMEdvMAXAmEGREAMJMA8EEIG5IAcCqEYlkEZVMA62AS1sAMaoBGa4RC0wTE4DefgElyB63AXBmAYnsIYvIYJBEHICBNhITqIEWKO2CLOCBeZjgQiYUg0koCkIOmIFFEixchypAKpQmqRXUgj8i1yFDmNXED6kNvIIDKK/Iq8RzGUgbJRA9QCdUC5qB8aisagc9F0NA9dgJaia9EatB49gLaip9FL6HV0AH2KjmOA0TEOZozZYVyMh0VgiVgaJscWY+VYNVaPNWMdWDd2FRvAnmHvCCQCi4AT7AhehBDCbIKQkEdYTFhDqCXsI7QSughXCYOEMcInIpOoT7QlehL5xHhiOrGQWEasJu4hHiGeJV4nDhNfk0gkDsmS5E4KISWQMkkLSWtI20gtpFOkPtIQaZxMJuuQbcne5AiygKwgl5G3kA+QT5L7ycPktxQ6xYjiTAmiJFKklBJKNWU/5QSlnzJCmaCqUc2pntQIqog6n1pJbaB2UC9Th6kTNHWaJc2bFkPLpC2j1dCaaWdp92gv6XS6Cd2DHkWX0JfSa+gH6efpg/R3DA2GDYPHSGIoGWsZexmnGLcZL5lMpgXTl5nIVDDXMhuZZ5gPmG9VWCr2KnwVkcoSlTqVVpV+leeqVFVzVT/VeaoLVKtVD6teVn2mRlWzUOOpCdQWq9WpHVW7qTauzlJ3Uo9Qz1Ffo75f/YL6Yw2yhoVGoIZIo1Rjt8YZjSEWxjJl8VhC1nJWA+ssa5hNYluy+exMdgX7G3Yve0xTQ3OqZqxmkWad5nHNAQ7GseDwOdmcSs4hzg3Oey0DLT8tsdZqrWatfq032nravtpi7XLtFu3r2u91cJ1AnSyd9TptOvd1Cbo2ulG6hbrbdc/qPtNj63npCfXK9Q7p3dFH9W30o/UX6u/W79EfNzA0CDaQGWwxOGPwzJBj6GuYabjR8IThqBHLaLqRxGij0UmjJ7gm7odn4zV4Fz5mrG8cYqw03mXcazxhYmky26TEpMXkvinNlGuaZrrRtNN0zMzILNys2KzJ7I451ZxrnmG+2bzb/I2FpUWcxUqLNovHltqWfMsFlk2W96yYVj5WeVb1VtesSdZc6yzrbdZXbFAbV5sMmzqby7aorZutxHabbd8U4hSPKdIp9VNu2jHs/OwK7JrsBu059mH2JfZt9s8dzBwSHdY7dDt8cnR1zHZscLzrpOE0w6nEqcPpV2cbZ6FznfM1F6ZLkMsSl3aXF1Ntp4qnbp96y5XlGu660rXT9aObu5vcrdlt1N3MPcV9q/tNLpsbyV3DPe9B9PD3WOJxzOOdp5unwvOQ5y9edl5ZXvu9Hk+znCae1jBtyNvEW+C9y3tgOj49ZfrO6QM+xj4Cn3qfh76mviLfPb4jftZ+mX4H/J77O/rL/Y/4v+F58hbxTgVgAcEB5QG9gRqBswNrAx8EmQSlBzUFjQW7Bi8MPhVCDAkNWR9yk2/AF/Ib+WMz3GcsmtEVygidFVob+jDMJkwe1hGOhs8I3xB+b6b5TOnMtgiI4EdsiLgfaRmZF/l9FCkqMqou6lG0U3RxdPcs1qzkWftnvY7xj6mMuTvbarZydmesamxSbGPsm7iAuKq4gXiH+EXxlxJ0EyQJ7YnkxNjEPYnjcwLnbJoznOSaVJZ0Y67l3KK5F+bpzsuedzxZNVmQfDiFmBKXsj/lgyBCUC8YT+Wnbk0dE/KEm4VPRb6ijaJRsbe4SjyS5p1WlfY43Tt9Q/pohk9GdcYzCU9SK3mRGZK5I/NNVkTW3qzP2XHZLTmUnJSco1INaZa0K9cwtyi3T2YrK5MN5Hnmbcobk4fK9+Qj+XPz2xVshUzRo7RSrlAOFkwvqCt4WxhbeLhIvUha1DPfZv7q+SMLghZ8vZCwULiws9i4eFnx4CK/RbsWI4tTF3cuMV1SumR4afDSfctoy7KW/VDiWFJV8mp53PKOUoPSpaVDK4JXNJWplMnLbq70WrljFWGVZFXvapfVW1Z/KheVX6xwrKiu+LBGuObiV05f1Xz1eW3a2t5Kt8rt60jrpOturPdZv69KvWpB1dCG8A2tG/GN5RtfbUredKF6avWOzbTNys0DNWE17VvMtqzb8qE2o/Z6nX9dy1b9rau3vtkm2ta/3Xd78w6DHRU73u+U7Ly1K3hXa71FffVu0u6C3Y8aYhu6v+Z+3bhHd0/Fno97pXsH9kXv62p0b2zcr7+/sgltUjaNHkg6cOWbgG/am+2ad7VwWioOwkHlwSffpnx741Dooc7D3MPN35l/t/UI60h5K9I6v3WsLaNtoD2hve/ojKOdHV4dR763/37vMeNjdcc1j1eeoJ0oPfH55IKT46dkp56dTj891JncefdM/JlrXVFdvWdDz54/F3TuTLdf98nz3uePXfC8cPQi92LbJbdLrT2uPUd+cP3hSK9bb+tl98vtVzyudPRN6zvR79N/+mrA1XPX+NcuXZ95ve/G7Bu3bibdHLgluvX4dvbtF3cK7kzcXXqPeK/8vtr96gf6D+p/tP6xZcBt4PhgwGDPw1kP7w4Jh57+lP/Th+HSR8xH1SNGI42PnR8fGw0avfJkzpPhp7KnE8/Kflb/eetzq+ff/eL7S89Y/NjwC/mLz7+ueanzcu+rqa86xyPHH7zOeT3xpvytztt977jvut/HvR+ZKPxA/lDz0fpjx6fQT/c+53z+/C/3hPP7btcu4QAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAB3RJTUUH5AoZDjUac23lRgAAACJ0RVh0U29mdHdhcmUAQWRvYmXCriBQaG90b3Nob3DCriBUb3VjaOLO2UAAAAWXSURBVHic7ZxvSBtnHMd/ubvlz12MGiTTtUs002lLnGFmNXoj1QhVqYuo01Jti3Pl8MUKZfiiZVLZYG9WJt0LEcHBXvimMsYQhEH7piAtDLZ3LWzrYPhCR91wk8W2S+OzFzMjSS/mkueeP679wBcOktzv+/t6T+65yz0CMCQYDM44nc5NAEBGZLVakdvtvnX16tUaVp6pghBySpK0DQYDyiVBEBKDg4OX6HdAAU3TZJfL9SdghqQnTdNOUG2GJG1tbe8DgZDS5fF4NjRNe4FeVwSoqqq6C4SDStf58+dfpNSauSiKQmTY5dPExEQ1jf5Mw+FwxIFBUCnNzc1Vk+/SBLxe7y/AMKiUzp49q5DuFQtVVT8EDoICAGS32/8m3S8OInAQUrqampq+IttykVit1gRwEFC2YrFYCdHGC2V5efl14CAYPSmKskuy94Kx2Wx/AAfB5BJCSCDYvnHm5+dLgYNA9pPX671LLoECOHTo0HXgIJD9ZLFY+BiKoig+AQ4CyafV1dWXiIVQAMyDMKJAILBILAEjqKoa1jPGo5xO532cXrHPEJubmyHcfdAikUi8gvN57LB2d3dbcPdBi8ePH2N9HjusjY0NF+4+DgrYYcXjcTN8HAiww/J4PGb4OBBghyVJ0g9mGDkIYIcliuJ3Zhihgd1ux/rOwA4rHA5/i7sPWoiiyMX1IfMJpxG1trZOEkvAKKWlpQ+AgzAMCAtT7vH4/f5PzdgPSSRJwpuRmgzro2ZfRaPRaYK9F0Z5efk94CCUfcQdrAPRlaqqbG/N6BEIBFaBg3B0xC2sg8lQX19fF+F+i2dkZMQHHIQEAMjr9d4g3S82oVBIA8ZBORyObfKdmoTX670GjIKSJOkRjR5Npbm5mXpgiqL8Rac7AvT09JwASkH5fL47tPoixuTkpFJWVvYICAbV1dXVR7El8ly+fPk4mBxSQ0PDT3S7oMyVK1f6ZVn+HYoMSBTFpN/v/5KFd6YcO3bsvfLy8juiKO4bkNvt/rW6unp+dnb2ZZx6ExMTbH40QAhZKisr7zEpXgQdHR0xAECRSCRKvbggCE8AAB09epT7+/A9PT0uSDtSNU37hErhhYUFN2QNk87OzgUqxYtgaWlJ91nX7u7u60QLDw8Pq3qFAQDV1tZ+TLR4EVy8eLHMYrHk/C4kdvumv79/IFfRlPx+PzcXr6dPn34NDJxd6+rqPjK18Llz5942UhgAkMvleoAQEk01UCDRaPSDXP70pKrqu6YUnpmZeauQwgCABEFA09PTEVMMFIiiKEXN4RYXFxuxCsdiMUOHci7Jspxob2+3Y5kwSHd39yUcrwCALly4YCuq+NjY2Bu4xVM6fPjw95qmVRRlJA+tra3jZvkUBCFZ8GPgAwMDPrMMpKuiouJhY2PjO4UGks3o6Gh9TU3NTRIeS0pK1g0b2TsUTTeRLUVR4h6P55uRkZHRfJ6mpqbqA4HAZy6X6z4Nb0eOHPk824NFz5gsy4mdnR0pXwP/dxBCYvrz87pj83lQ/9LS0pJxdnwqrNu3bzvo2eGb7e3t3JPVpaUlazAY/AIofCccBO1dJukTiUSikiRxvbqLgf4jYxiura0dRwjxtZCRMevr677UdkZY8Xg8lEwm+ViXxwmhUGgstZ0RTCKReJW6G85JJpPtqe2MsKxWq++pdz/jVFZWtqe2s4fc8/lVFunrfbKH4W/U3XDO3tpvAMgKy2az/UzfDt9sbW1dS21nhCXL8i36dvhmZWXla90XTp482SyK4kNgPxHkSblpamri9blQ6goGg4Z++WFulAcNDQ258ya194ghc7Ms5fF4nrpbqntpc+rUqWdnxWUOpqamGgy/ube3903g4C/MQr29vYX/d8ozZ87UsjZOW+Pj4/UFB5VOOBy+wboJ0goEAj/my+Efl9ZrqHsUrjsAAAAASUVORK5CYII=";
    String base64Image2 = base64String2.split(",")[1];
    //tamanho
    String base64String3 = "data:image/png;base64,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";
    String base64Image3 = base64String3.split(",")[1];
    //tamanho
    String base64String4 = "data:image/png;base64,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";
    String base64Image4 = base64String4.split(",")[1];
    CheckBox showChkBox;

    // nao modifique os nomes dos edtext e buttoon
    EditText mail, pass;
    Button init, init2;

    ImageView imageView2; //username
    ImageView imageView3; //pass
    ImageView imageView4; // button
//dont modify my url credits hahahah


    private void SetupForm() {
        float[] outerRadii = new float[]{20,20,20,20,20,20,20,20};
        float[] innerRadii = new float[]{20,20,20,20,20,20,20,20};
        ShapeDrawable shape = new ShapeDrawable(new RoundRectShape(outerRadii, null, innerRadii));
        shape.getPaint().setColor(Color.BLUE);
        shape.getPaint().setStyle(Paint.Style.STROKE);
        shape.getPaint().setStrokeWidth(3);
        shape.setPadding(3,3,3,3);

        ShapeDrawable shape2 = new ShapeDrawable(new RoundRectShape(outerRadii, null, innerRadii));
        shape2.getPaint().setColor(Color.RED);
        shape2.getPaint().setStyle(Paint.Style.STROKE);
        shape2.getPaint().setStrokeWidth(5);
        shape2.setPadding(1,1,1,1);
        ShapeDrawable shape3 = new ShapeDrawable(new RoundRectShape(outerRadii, null, innerRadii));
        shape3.getPaint().setColor(Color.BLUE);
        shape3.getPaint().setStyle(Paint.Style.STROKE);
        shape3.getPaint().setStrokeWidth(5);
        ShapeDrawable shape4 = new ShapeDrawable(new RoundRectShape(outerRadii, null, innerRadii));
        shape4.getPaint().setColor(Color.BLUE);
        shape4.getPaint().setStyle(Paint.Style.STROKE);
        shape4.getPaint().setStrokeWidth(5);
        //shape2.setPadding(1,1,1,1);

        GradientDrawable gd = new GradientDrawable(
			GradientDrawable.Orientation.LEFT_RIGHT,
			new int[] {Color.DKGRAY, Color.parseColor("#000000")});
        gd.setCornerRadius(0f);
        //gd2
        GradientDrawable gd2 = new GradientDrawable();
        gd2.setColor(Color.WHITE);
        gd2.setStroke(5, Color.RED);
        gd2.setCornerRadius(8);

        //Add relative layout
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setBackground(gd);
        linearLayout.setPadding(convertDipToPixels(15.0f), convertDipToPixels(15.0f), convertDipToPixels(15.0f), convertDipToPixels(15.0f));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        //add view form
        LinearLayout linearLayout2 = new LinearLayout(this);
        // use esse pois é mais compativel,entretanto as imagens tem de ser maior
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(convertDipToPixels(400.0f), -2);
        //RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(1000, 650);


        linearLayout2.setBackgroundColor(Color.WHITE);
        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
        rlp.addRule(CENTER_IN_PARENT);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
        linearLayout2.setLayoutParams(rlp);
        //relativeLayout.setBackground(gd2);
        linearLayout2.setBackground(gd2);
        //   linearLayout2.setBackgroundColor(Color.WHITE);



        //define img edit user
        ImageView imageView2 = new ImageView(this);
        byte[] decodedString2 = Base64.decode(base64Image2, Base64.DEFAULT);
        Bitmap decodedByte2 = BitmapFactory.decodeByteArray(decodedString2, 0, decodedString2.length);
        imageView2.setImageBitmap(decodedByte2);

        //define img edit pass
        ImageView imageView3 = new ImageView(this);
        byte[] decodedString3 = Base64.decode(base64Image3, Base64.DEFAULT);
        Bitmap decodedByte3 = BitmapFactory.decodeByteArray(decodedString3, 0, decodedString3.length);
        imageView3.setImageBitmap(decodedByte3);

        //login
        ImageView imageView4 = new ImageView(this);
        byte[] decodedString4 = Base64.decode(base64Image4, Base64.DEFAULT);
        Bitmap decodedByte4 = BitmapFactory.decodeByteArray(decodedString4, 0, decodedString4.length);
        imageView4.setImageBitmap(decodedByte4);

        //create drawable
        // Drawable d = new BitmapDrawable(decodedByte2);
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(decodedByte2, 50, 50, true));
        Drawable d2 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(decodedByte3, 50, 50, true));
        Drawable d3 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(decodedByte4, 50, 50, true));

        TextView title = new TextView(this);
        title.setTextSize(46.0f);
        title.setGravity(Gravity.CENTER);
        title.setText(TeamName());
//title.setBackground(shape2);
        title.setTextColor(Color.RED);
        title.setPadding(5,3,5,3);
        //test change img view size
        //create the checkbox show
        showChkBox = new CheckBox(this);
        RelativeLayout.LayoutParams rlp4 = new RelativeLayout.LayoutParams(-1, -2);
        rlp4.setMargins(8,0,0,5);
        showChkBox.setLayoutParams(rlp4);
        // showChkBox.setChecked(isChecked);
        showChkBox.setPadding(0, 5, 0, 8);
        showChkBox.setTextSize(18);
        showChkBox.setTextColor(Color.BLACK);
        if (Build.VERSION.SDK_INT > 21) {
            showChkBox.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#f400a1")));//setButtonTintList is accessible directly on API>19
        }
        showChkBox.setText("Exibir Senha");

        showChkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
					} else {
						pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
					}
				}
			});

        //Username form
        mail = new EditText(this);
        mail.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        mail.setHint("Usuario:");
        mail.setHintTextColor(Color.parseColor("#000000"));
        mail.setTextColor(Color.parseColor("#000000"));
        mail.setSingleLine(true);
        mail.setBackground(shape);
        mail.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
        mail.setCompoundDrawablePadding(11);
        // mail.setBackground(gd2);
        mail.setPadding(convertDipToPixels(15.0f), convertDipToPixels(15.0f), convertDipToPixels(15.0f), convertDipToPixels(15.0f));
        mail.setFilters(new InputFilter[]{new InputFilter.LengthFilter(32)});

        //Password form
        pass = new EditText(this);
        LinearLayout.LayoutParams rlp2 = new LinearLayout.LayoutParams(-1, -2);
        pass.setHint("Senha:");
        rlp2.setMargins(15,10,15,3);
        pass.setHintTextColor(Color.parseColor("#000000"));
        pass.setTextColor(Color.parseColor("#000000"));
        pass.setSingleLine(true);
        pass.setBackground(shape);
        pass.setCompoundDrawablesWithIntrinsicBounds(d2, null, null, null);
        pass.setCompoundDrawablePadding(11);
        // pass.setBackground(gd2);
        pass.setLayoutParams(rlp2);
        mail.setLayoutParams(rlp2);
        pass.setPadding(convertDipToPixels(15.0f), convertDipToPixels(15.0f), convertDipToPixels(15.0f), convertDipToPixels(15.0f));
        pass.setFilters(new InputFilter[]{new InputFilter.LengthFilter(30)});
        pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        //pass.setInputType(129);

        //button
        init = new Button(this);
        RelativeLayout.LayoutParams rlp3 = new RelativeLayout.LayoutParams(-10, convertDipToPixels(40.0f));
        init.setBackgroundColor(Color.TRANSPARENT);
        init.setText("LOGAR ->");
        init.setTextSize(19.0f);
        init.setBackground(shape4);
        // init.setPadding(convertDipToPixels(15.0f), convertDipToPixels(15.0f), convertDipToPixels(15.0f), convertDipToPixels(15.0f));
        init.setGravity(Gravity.CENTER);
        rlp3.setMargins(convertDipToPixels(70.0f),0,0,convertDipToPixels(11.0f));
        // init.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,d3,null);
        init.setPadding(0,0,0,0);
        init.setLayoutParams(rlp3);
        init.setTextColor(Color.parseColor("#000000"));

        init2 = new Button(this);
        RelativeLayout.LayoutParams rlp44 = new RelativeLayout.LayoutParams(-10, convertDipToPixels(40.0f));
        init2.setBackgroundColor(Color.parseColor("#FFD700"));
        init2.setText(" CANCELAR ");
        init2.setTextSize(19.0f);
        init2.setBackground(shape3);
        init2.setPadding(0,0,0,0);
        // init.setPadding(convertDipToPixels(15.0f), convertDipToPixels(15.0f), convertDipToPixels(15.0f), convertDipToPixels(15.0f));
        init2.setGravity(Gravity.CENTER);
        rlp44.setMargins(convertDipToPixels(60.0f),0,0,convertDipToPixels(11.0f));
        // init.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,d3,null);
        init2.setLayoutParams(rlp44);
        init2.setTextColor(Color.parseColor("#000000"));

        //Footer text
        LinearLayout linearLayout3 = new LinearLayout(this);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-4, -3);
        // layoutParams5.addRule(12);
        linearLayout3.setLayoutParams(layoutParams5);
        linearLayout3.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout3.setBackgroundColor(Color.TRANSPARENT);

        TextView footerText = new TextView(this);
        footerText.setText(Html.fromHtml("<font face='monospace'>Tela de login by <font color='#ff0000'><b>Hyupai Mods<b></font></font>"));
        footerText.setTextColor(Color.parseColor("#00ff00"));
        footerText.setGravity(17);
        footerText.setTextSize(17.0f);
        //linearLayout3.addView(footerText);

        footerText.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(URL() + "/S4muu")));
				}
			});

        //Add views
        //linearLayout2.addView(imageView);
        linearLayout2.addView(title);
        linearLayout2.addView(mail);
        linearLayout2.addView(pass);
        linearLayout2.addView(showChkBox);
        //linearLayout2.addView(init);
        relativeLayout.addView(linearLayout);
        relativeLayout.addView(linearLayout2);
        linearLayout2.addView(linearLayout3);
        linearLayout3.addView(init);
        linearLayout3.addView(init2);

        //OnClick listeners
        init2.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					finishAffinity();
				}
			});


        setContentView(relativeLayout);
        TryLoginPHP();
    }

    private final String USER = "USER";
    private final String PASS = "PASS";
    private Prefs prefs;
    private void TryLoginPHP() {
        prefs = Prefs.with(this);

        mail.setText(prefs.read(USER, ""));
        pass.setText(prefs.read(PASS, ""));


        init.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {

					Intent i = new Intent(getApplicationContext(), Auth.class);
					i.putExtra("user", pass.getText().toString());

					if (!mail.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()) {
						prefs.write(USER, mail.getText().toString());
						prefs.write(PASS, pass.getText().toString());
						new Auth(MainActivity.this).execute(mail.getText().toString(), pass.getText().toString());
					}
					if (mail.getText().toString().isEmpty() && pass.getText().toString().isEmpty()) {
						mail.setError("Usuario requirido!");
						pass.setError("Senha requirida!");
					}
					if (mail.getText().toString().isEmpty()) {
						mail.setError("Usuario Requirido!");
					}
					if (pass.getText().toString().isEmpty()) {
						pass.setError("Senha requirida!");
					}
				}
			});



    }
    private int convertDipToPixels(float f) {
        return (int) ((f * getResources().getDisplayMetrics().density) + 0.5f);
    }



    public String urlRequest(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(str).openConnection().getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append("\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //comente ou descomente essa parte


        loadLibrary("MyLibName");
       // prefs = Prefs.with(this);
        //StaticActivity.Start(this);

        new AlertDialog.Builder(this)
			.setTitle("Aviso!")
			.setMessage("" + Dialog())
			.setPositiveButton("Ok!", null)
			.setNegativeButton("Grupo", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialogInterface, int i) {
					Intent intent = new Intent("android.intent.action.VIEW");
					intent.setData(Uri.parse(URL()));
					Intent intent2 = Intent.createChooser(intent, "Abrir");
					MainActivity.this.startActivity(intent2);
					dialogInterface.cancel();
				}
			})
			.show();
        SetupForm();

    }
}

