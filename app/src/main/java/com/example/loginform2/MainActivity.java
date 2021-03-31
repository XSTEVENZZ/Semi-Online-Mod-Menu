package com.example.loginform2;


import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
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
import android.provider.MediaStore;
import android.text.Html;
import android.text.InputFilter;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Process;
import android.provider.Settings;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import uk.lgl.modmenu.StaticActivity;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.RelativeLayout.ALIGN_PARENT_BOTTOM;
import static android.widget.RelativeLayout.ALIGN_PARENT_LEFT;
import static android.widget.RelativeLayout.ALIGN_PARENT_RIGHT;
import static android.widget.RelativeLayout.CENTER_HORIZONTAL;
import static android.widget.RelativeLayout.CENTER_IN_PARENT;
import static android.widget.RelativeLayout.CENTER_VERTICAL;
import static java.lang.System.loadLibrary;

public class MainActivity extends Activity {
    public String sGameActivity = "uk.lgl.modmenu.MainActivity";
    private native String YTURL();
    private native String Dialog();



    String base64String2 = "data:image/png;base64,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";
    String base64Image2 = base64String2.split(",")[1];
    String base64String3 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEsAAABLCAYAAAA4TnrqAAAABGdBTUEAALGPC/xhBQAACk1pQ0NQUGhvdG9zaG9wIElDQyBwcm9maWxlAAB4nJ1Td1iT9xY+3/dlD1ZC2PCxl2yBACIjrAjIEFmiEJIAYYQQEkDFhYgKVhQVEZxIVcSC1QpInYjioCi4Z0GKiFqLVVw47h/cp7V9eu/t7fvX+7znnOf8znnPD4AREiaR5qJqADlShTw62B+PT0jEyb2AAhVI4AQgEObLwmcFxQAA8AN5eH50sD/8Aa9vAAIAcNUuJBLH4f+DulAmVwAgkQDgIhLnCwGQUgDILlTIFADIGACwU7NkCgCUAABseXxCIgCqDQDs9Ek+BQDYqZPcFwDYohypCACNAQCZKEckAkC7AGBVgVIsAsDCAKCsQCIuBMCuAYBZtjJHAoC9BQB2jliQD0BgAICZQizMACA4AgBDHhPNAyBMA6Aw0r/gqV9whbhIAQDAy5XNl0vSMxS4ldAad/Lw4OIh4sJssUJhFykQZgnkIpyXmyMTSOcDTM4MAAAa+dHB/jg/kOfm5OHmZuds7/TFov5r8G8iPiHx3/68jAIEABBOz+/aX+Xl1gNwxwGwdb9rqVsA2lYAaN/5XTPbCaBaCtB6+Yt5OPxAHp6hUMg8HRwKCwvtJWKhvTDjiz7/M+Fv4It+9vxAHv7bevAAcZpAma3Ao4P9cWFudq5SjufLBEIxbvfnI/7HhX/9jinR4jSxXCwVivFYibhQIk3HeblSkUQhyZXiEul/MvEflv0Jk3cNAKyGT8BOtge1y2zAfu4BAosOWNJ2AEB+8y2MGguRABBnNDJ59wAAk7/5j0ArAQDNl6TjAAC86BhcqJQXTMYIAABEoIEqsEEHDMEUrMAOnMEdvMAXAmEGREAMJMA8EEIG5IAcCqEYlkEZVMA62AS1sAMaoBGa4RC0wTE4DefgElyB63AXBmAYnsIYvIYJBEHICBNhITqIEWKO2CLOCBeZjgQiYUg0koCkIOmIFFEixchypAKpQmqRXUgj8i1yFDmNXED6kNvIIDKK/Iq8RzGUgbJRA9QCdUC5qB8aisagc9F0NA9dgJaia9EatB49gLaip9FL6HV0AH2KjmOA0TEOZozZYVyMh0VgiVgaJscWY+VYNVaPNWMdWDd2FRvAnmHvCCQCi4AT7AhehBDCbIKQkEdYTFhDqCXsI7QSughXCYOEMcInIpOoT7QlehL5xHhiOrGQWEasJu4hHiGeJV4nDhNfk0gkDsmS5E4KISWQMkkLSWtI20gtpFOkPtIQaZxMJuuQbcne5AiygKwgl5G3kA+QT5L7ycPktxQ6xYjiTAmiJFKklBJKNWU/5QSlnzJCmaCqUc2pntQIqog6n1pJbaB2UC9Th6kTNHWaJc2bFkPLpC2j1dCaaWdp92gv6XS6Cd2DHkWX0JfSa+gH6efpg/R3DA2GDYPHSGIoGWsZexmnGLcZL5lMpgXTl5nIVDDXMhuZZ5gPmG9VWCr2KnwVkcoSlTqVVpV+leeqVFVzVT/VeaoLVKtVD6teVn2mRlWzUOOpCdQWq9WpHVW7qTauzlJ3Uo9Qz1Ffo75f/YL6Yw2yhoVGoIZIo1Rjt8YZjSEWxjJl8VhC1nJWA+ssa5hNYluy+exMdgX7G3Yve0xTQ3OqZqxmkWad5nHNAQ7GseDwOdmcSs4hzg3Oey0DLT8tsdZqrWatfq032nravtpi7XLtFu3r2u91cJ1AnSyd9TptOvd1Cbo2ulG6hbrbdc/qPtNj63npCfXK9Q7p3dFH9W30o/UX6u/W79EfNzA0CDaQGWwxOGPwzJBj6GuYabjR8IThqBHLaLqRxGij0UmjJ7gm7odn4zV4Fz5mrG8cYqw03mXcazxhYmky26TEpMXkvinNlGuaZrrRtNN0zMzILNys2KzJ7I451ZxrnmG+2bzb/I2FpUWcxUqLNovHltqWfMsFlk2W96yYVj5WeVb1VtesSdZc6yzrbdZXbFAbV5sMmzqby7aorZutxHabbd8U4hSPKdIp9VNu2jHs/OwK7JrsBu059mH2JfZt9s8dzBwSHdY7dDt8cnR1zHZscLzrpOE0w6nEqcPpV2cbZ6FznfM1F6ZLkMsSl3aXF1Ntp4qnbp96y5XlGu660rXT9aObu5vcrdlt1N3MPcV9q/tNLpsbyV3DPe9B9PD3WOJxzOOdp5unwvOQ5y9edl5ZXvu9Hk+znCae1jBtyNvEW+C9y3tgOj49ZfrO6QM+xj4Cn3qfh76mviLfPb4jftZ+mX4H/J77O/rL/Y/4v+F58hbxTgVgAcEB5QG9gRqBswNrAx8EmQSlBzUFjQW7Bi8MPhVCDAkNWR9yk2/AF/Ib+WMz3GcsmtEVygidFVob+jDMJkwe1hGOhs8I3xB+b6b5TOnMtgiI4EdsiLgfaRmZF/l9FCkqMqou6lG0U3RxdPcs1qzkWftnvY7xj6mMuTvbarZydmesamxSbGPsm7iAuKq4gXiH+EXxlxJ0EyQJ7YnkxNjEPYnjcwLnbJoznOSaVJZ0Y67l3KK5F+bpzsuedzxZNVmQfDiFmBKXsj/lgyBCUC8YT+Wnbk0dE/KEm4VPRb6ijaJRsbe4SjyS5p1WlfY43Tt9Q/pohk9GdcYzCU9SK3mRGZK5I/NNVkTW3qzP2XHZLTmUnJSco1INaZa0K9cwtyi3T2YrK5MN5Hnmbcobk4fK9+Qj+XPz2xVshUzRo7RSrlAOFkwvqCt4WxhbeLhIvUha1DPfZv7q+SMLghZ8vZCwULiws9i4eFnx4CK/RbsWI4tTF3cuMV1SumR4afDSfctoy7KW/VDiWFJV8mp53PKOUoPSpaVDK4JXNJWplMnLbq70WrljFWGVZFXvapfVW1Z/KheVX6xwrKiu+LBGuObiV05f1Xz1eW3a2t5Kt8rt60jrpOturPdZv69KvWpB1dCG8A2tG/GN5RtfbUredKF6avWOzbTNys0DNWE17VvMtqzb8qE2o/Z6nX9dy1b9rau3vtkm2ta/3Xd78w6DHRU73u+U7Ly1K3hXa71FffVu0u6C3Y8aYhu6v+Z+3bhHd0/Fno97pXsH9kXv62p0b2zcr7+/sgltUjaNHkg6cOWbgG/am+2ad7VwWioOwkHlwSffpnx741Dooc7D3MPN35l/t/UI60h5K9I6v3WsLaNtoD2hve/ojKOdHV4dR763/37vMeNjdcc1j1eeoJ0oPfH55IKT46dkp56dTj891JncefdM/JlrXVFdvWdDz54/F3TuTLdf98nz3uePXfC8cPQi92LbJbdLrT2uPUd+cP3hSK9bb+tl98vtVzyudPRN6zvR79N/+mrA1XPX+NcuXZ95ve/G7Bu3bibdHLgluvX4dvbtF3cK7kzcXXqPeK/8vtr96gf6D+p/tP6xZcBt4PhgwGDPw1kP7w4Jh57+lP/Th+HSR8xH1SNGI42PnR8fGw0avfJkzpPhp7KnE8/Kflb/eetzq+ff/eL7S89Y/NjwC/mLz7+ueanzcu+rqa86xyPHH7zOeT3xpvytztt977jvut/HvR+ZKPxA/lDz0fpjx6fQT/c+53z+/C/3hPP7btcu4QAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAB3RJTUUH5AoZDjYh6UtfoQAAACJ0RVh0U29mdHdhcmUAQWRvYmXCriBQaG90b3Nob3DCriBUb3VjaOLO2UAAAA4GSURBVHic7ZxpkFzVdcd/575eZkMaaSSNRgtgtIEkJBRJBDvEGFKuxBgcICmwXQJXgeOQD5SxQwyUbXCMnbjskEhAxcaVuMAf4qICjhQJTFlgDFjCkSWNdrRESEizaXqmW5rpbbr73ZMPbzQzPdOj7n7dmrFx/p+673Lueeede9//nrsIEwSFAKyaBc48cOeDuQwjS1BdZIUWA9MtUm8gDBjAtZACTaJEDNKG6DEsx0FPAO+DdkJrr4CdiGeQi92AcnkNNK0EWYbRtahcjbAcuAQRU75AtUC/VT1khD1Y2Ql6EHr3CifTVX+AEbhoxlKubIL626zIp4zIVRadY6AOkeq1qaoWkgbpsKqHjOoWSPyXcLi3am2MQNWNpRCC1fdbY/7OQAuoqaqBxm1YFcRatNNY/SfY9QOBgWo2UbWHUFbOhcAnrMiDxsiyasn1DWsPoboe9GWhtaMaIis2ljKvFmZ/0oreZ8TcgFBbDcWqAiVp1b5llB/BmS1CW6oScRUZS1nejAk/ZpG7DEyfkO5WLrxxLWrQF7CpJ4RDXX5F+Xo4BQdWLERCz2PkWr9yUEUdA7VhqHGwM6ZimxqgXpFMDtObRDr6YMCFVAZxFSp4H9bqDqOZe2DfMT90o+yWlaUNEL4dCfwjhrnl1veEKBo06NLL0euWw0dXwdqrkfktmFAIMu2Q7UBV0Y6z0HoC3j6KbD8O73YhGde/0ax2oPZhSG8UDsXLqVpWi56hah+wRr5kRGaWp+UwbHMjev8dyJ9/DLlyARIK5hcYNNZIg6hr4Wgn+rN9yLNvIp19fpvHqkaM1X+B1NPlGKxMY635KoavAyG/45N76TT4zycxK68ExykspoCxYJAduBY9dgY+86+Y96J+VBgURAZrvyns/odSq5X0wJ5H1X0Rwzd9sW5AAw72plXw/BM4M5vG5FvXJdPXRy6VQtMdGIkSqA0RaqhHzFg1tT+F3vdvyOtHkKzP2Y6qxerXSvWwosY63/Uw8jgiYV86GcHeuAp56mFk8eV53uRms0R2tdL51jZ69x4g3dOLm4oTaHConTaFppULafnjFcxYvgDjDL8nVUVPRrAP/QTn9SOI39mh6gBWvwGpZ4oZ7ILG8r56qz5rjfOkgRl+u56d2YC++CTmwysRM/zA6ViM3zz+LU5t/jmpjm7cTAYZoZKiOOEQdXOmc9lt17P20XsI1Q/TOFUlveckA3/5FI0Rn2TdoxY9xtovw+7/uNBXsoixVixBQr/AyBx/moACue98geBDnx+pH2ePHuX1dV8gunN/ybJmXLeYP/n3rzHlsua89/buDzfR9NAmZmrYP9Wz2oFmbhL2HRmvyLjjj7J09iCP8m0oALviUpz7P5uX1nf8BNse+ArRXaUbCqBnxzG2PfQ0/W3deemLPnczu1c1cFqTuKr+FDUyx0rox8ry5nGLFEpU5tViah8bJJy+oQL6pbsxDXUjEpX9G75P5xvbPbcrB1Zpf203B57dlJccCAdZ8MU7eMf00q7JwY9d+TBGrsWEH/OmcAXyC1drvsUid1LhdMjOn45zw9q8tJ4DBzn8w+fQnOtLpmZdDj3738T+93Reesu1S7GXNfGmdhOpINhgkbug+ZZCeWOMpayca4V7DUz33SKAKly7HKZeMiJJ2f3t72Iz/gx1HjaZofWfX8hzzNDUBmZeu4QkObbYNtptEuvDwwxMt8K9yqoxw08Bz3Ju9qIHlU6KFRZd6s37BjHQ18fpLT+vTOwgTm56i2xyODDq1ISYcsU8QMkBb2k3pzVRvsFExIi5AczNo7PyjKUeM3+wGmEWxSIzGiEwPJXpO/4ebjpbqWgAcvE0fSc7h/6bgEPttIah//3k2K49dKqPqIxQa0Ue9AKZwxjlWav/BmOWli99LBQDgXAe+05Fo3k8qhKIGtJ9wxxSRAjUhDFOYCitnxyvagcRTZc96HsBzD/4q7y08z+UK5usMQ/51n5sc57BRsJWeRFmlAEk4CBOfpsu8Krt9EcrjPmKsnRo7B4huf52L2b+wUMKl3e0h7ayaYXMhdrbz/8z4C1XWZFbQX1Nkn8XcI4sb2s33ZSzWqbGitzqLecNeVbTSiNy1W9lWLiKSOLysm2n06ZK+0qKCCJXwdQVAMZbKWa5RSua1vyuIAe8oWc4VTKt0LkQXKYgBlbNwrDGQF3xih8MxMnxa+2howRaYaAOo2tgda0BZx4qKz7oXXA0+sixVTuJaJExTERQWQm2PgDufMSpeFFUZwTQ6cMEVAGmJCA7TByD4TiNS6Z68fQL6acpKPIQ4kDQaYeB+qG0cF030xa62BGR01RMSPcU9oMsyiu2g5ukmblShxnPX4RlqDaKsvpvMfJdv+Hi83DvnQXr8oc9md2EaZwy9D+TSBBvaysabZD0fiS9v+g0vn7ODII1wyR7oD9JKnI2r8z+l0K8+3xodNU8TCXIddLEfKkvbDBVi7WfDFjDElOhoQCkKYhZVD8qNQ3usIeEamD6wmnFhcVDEM+WEPPohBGzp3ANhOfnl6hpDFAM58iyTXu4URxaCs30RAzIKmOUhUWl/R4gTo6f2Xa6dBxaYVhurHwwWbsf5IDXbRenNTF2aqQsMBXHrT5gSODya+2lQ5P5GUKLscjogeb3HufI8gvtGkUr5BIzuIfz/zEKAyhbbDun7RDTrzdcYIVnoqGqxGNxuttSRHvGRGAmHFmUbRrxIq5oIIAX8plUg6kqvad62Ld1F72nIkT6EpxLQctcuOnjMP9DFe00qgh9gxHXG5iVC1hIGQgWr3ZxoFZpO3SKt3/8GvFYPyIQS0AkAWfOwInjcNc6uOrqyTNYPzm2alfOwOhhf2KROBtnx0vbhgw1EiJw7hxs/imc9blhplrIYPsMSmQylTix+zjRtu5xvUYEOjuhdefE6jUaBu0yBjldvOjFw4mdR4tOa0Rg746J0Wc8GDhlUD02mUpkkpmSyiUr2mdcOQR2G+D44BGPScHU2SVMrIHm2RdZkQtAvMWJPQb0JNA/WYosvn4ZRfuhwh9+dELUKQiBhMU9YoD3UQ5OliJzFs/hsmuuKEhAz6ctXQ6LlkysXqNwMI1GDWgnont979OpEIFwkLW3/xFXrFmMCTh5ecEgrLgGbv0LL1Y1OVA1sK+eeMJAay9WdlqYFL4lIjS2TOMjn/kY4dp8i4QDcMenobll8ggpSBrszrshabw9lHrQIFU5DORLHQQxhsyoTSNZ11t/msylFEE7BOeADK9A9+61qocmqysCnO2MoqP2vlqFM53jVJgQqCpyKEt0DwxOoIWTaaO6Gf8bpCtGrL1nzMYRa6GrbZIU8uCG4JW/HhyiRkQbEhstTMp7VFViXTGszXdsa73J9GT5u0Ei9WRfGv4/COFwr7H2e5OhVC7rEu/t8/rdCKhCLAqZ0kh+1RGG9XcSH5o7j4pj7fqBteqLc2nKfw9Onk2Q6ksWXE7s74O+mG/R2Ky/UJ2Bw43EnhmZlreoJpBR1fWobEDK2/ugFRx8z2Vy1F5ST9P8WZ4e8QTBRAIEGi6pzLMSbb6MlTLYDZ8aRacKrEDaV1C5A/izcvY/mONpbDKHqSu+qDkajc2NXL/uRqxrEcCNt+ImdntyDdQ3XLj+eMhmDL1Hy+MdHkXQX4XJbBmdN+bJhNYO1TU/ssJaAzNKbiSSw+7pRz/cWPYeEyfoUN84YpEpHPKWUSrgV6oQeTdAOlquZ2nMQZ5bR2rMd3gcSV0vG3iBcs5AxLKwNQrpSWMfecgNCO//Mkg6WuaLg40OsU2F8goaS2hLYdNPWKu/KbURySnyahS7f9ICGHnoORLgxGsBbK70Og7sCTPw+D2QKJQ/ro8KB84YzdyDpb2klkSQtgz8/XvYgcpOUFQK14V3NtQRbzMl92RBu+vIrivU/c6jSIfedwx1H7GqkVKmQgI4e1PYu/aRPRLH5ia2S1oXou8H2Hz/VCKtJY9VqmgkCI8GiB++UMELSvQm2emN3uFrSvuACzi7kmQfOMyZn5wkFZ2YeHC6z3BkSw1vfLWe7p3lHE3QTBCeDnL2xTu9NdRxUcYZ6doHMPKtUje9WZTe+hw75yeY/vFm5nxkNo0LplHbVFO81cQOiP9P0XKZRIDYCYeOnQFO/dKh/5Qh01/WgG5r4Hs5Yt++r4RocZmn71c/gpFvUMbp+26b5hXtYAAXxY6JLBSGAZwiZXSopI8jLgqaqUHWf47YI6VWKpNBpp7B1mKNfNlASfc6zDI1/Klt4W3t5ixZihuhVPgnYYr2BOHpHLH1F7VFr0vW3IY43yn1xhCrSoem2K6RQYNNHgTtDsKjQc6+uA7KuknD7100BlYsKucuGqtKt6Z5Rdspg/pUFQ7sCZG9u4b4u8UG80Kowi1HNV+38OlSbzmK2DRbtYv4BJlMvPEp5sDGMAOPX4hHlSCrMniHr5tvscK9pd6f1W6TbNcIsYveJW3aIL8KIs8ZYhvHY+alooo3s62aA+YTiDyIkeUXKmtV6dQUb2r3RfMwB44Y7AbIbLmXVFX2c1ykO/+u+Twm8LB3Xm/8O/8iNs1mbSdX9p0FBVtWwBWkJwwbphF7anQ8qlJcxNskl06HutsQbrUiS0HnFrpNsst6HnbOX5dUIC1oh0EOO7BlKtmf3kG8u2hNH5ige0qnrgBnGUbWDN5TejUwBRGjqrRrku3aUxKtEM+FkqAHA8heYKcgBwJE91Y6JpXQ9sRAQWB1LVAH7jQwC0CuwXC1q7qgi3TLVj0zM4s9H/ZTg2YtEjNol4FTCq0h2JPDPZxGo/XEE3dDUsq/e8QX/g9O2gDx/gBa9QAAAABJRU5ErkJggg==";
    String base64Image3 = base64String3.split(",")[1];


    CheckBox showChkBox;
    EditText mail, pass;
    Button init;
    private GradientDrawable gdAnimation = new GradientDrawable();
    private final GradientDrawable gdAnimation2 = new GradientDrawable();






    private void SetupForm() {
        float[] outerRadii = new float[]{20,20,20,20,20,20,20,20};
        float[] innerRadii = new float[]{20,20,20,20,20,20,20,20};
        ShapeDrawable shape = new ShapeDrawable(new RoundRectShape(outerRadii, null, innerRadii));
        shape.getPaint().setColor(Color.RED);
        shape.getPaint().setStyle(Paint.Style.STROKE);
        shape.getPaint().setStrokeWidth(5);
        shape.setPadding(3,3,3,3);




        //gd1
        GradientDrawable gd = new GradientDrawable();
       // gd.setColor(Color.YELLOW);
       gd.setCornerRadius(10);
       gd.setAlpha(50);
        //gd2
        GradientDrawable gd2 = new GradientDrawable();
        gd2.setColor(Color.WHITE);
        gd2.setCornerRadius(20);
        //gd2.setAlpha(120);




final TextView name = new TextView(this);
name.setText("Hyupai");
name.setTextSize(40.0f);
    //  name.setAnimation();
     // name.startAnimation(animation);
        LinearLayout.LayoutParams name1 = new LinearLayout.LayoutParams(-10,-10);
        name1.gravity = 17;
        name1.setMargins(0,convertDipToPixels(0.0f),0,0);
        name.setLayoutParams(name1);

        int colorFrom = Color.RED;
        int colorTo = Color.YELLOW;
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(5000); // milliseconds
        colorAnimation.setRepeatCount(ValueAnimator.INFINITE);
        colorAnimation.setRepeatMode(ValueAnimator.REVERSE);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                name.setTextColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();


        //Add relative layout
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setBackground(gdAnimation);
     //   linearLayout.setPadding(convertDipToPixels(15.0f), convertDipToPixels(15.0f), convertDipToPixels(15.0f), convertDipToPixels(15.0f));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        //add view form
        LinearLayout linearLayout2 = new LinearLayout(this);
        // use esse pois é mais compativel,entretanto as imagens tem de ser maior
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(convertDipToPixels(400.0f), -2);
        //RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(1000, 650);

        linearLayout2.setBackgroundColor(Color.rgb(255, 255, 255));
        //linearLayout2.setAlpha(07);
        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
        rlp.addRule(CENTER_IN_PARENT);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
        linearLayout2.setLayoutParams(rlp);
        linearLayout2.setBackground(gd2);


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

        //create drawable
        // Drawable d = new BitmapDrawable(decodedByte2);
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(decodedByte2, 50, 50, true));
        Drawable d2 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(decodedByte3, 50, 50, true));
        //     Drawable d3 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(decodedByte4, 50, 50, true));
LinearLayout linearLayoutc = new LinearLayout(this);
        linearLayoutc.setBackgroundColor(Color.TRANSPARENT);
        linearLayoutc.setOrientation(LinearLayout.HORIZONTAL);


        RadioButton rd = new RadioButton(this);
        //preguiça de por margin
        rd.setText("Exibir Senha         ");
        if (Build.VERSION.SDK_INT > 21) {
            rd.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));//setButtonTintList is accessible directly on API>19
        }

        rd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        RadioButton rd2 = new RadioButton(this);
        rd2.setText("Salvar Login");
        if (Build.VERSION.SDK_INT > 21) {
            rd2.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));//setButtonTintList is accessible directly on API>19
        }

        rd2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    prefs.write(USER, mail.getText().toString());
                    prefs.write(PASS, pass.getText().toString());
                } else {
                   // pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
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
        RelativeLayout.LayoutParams rlp3 = new RelativeLayout.LayoutParams(-1, convertDipToPixels(40.0f));
        init.setText("Login");
        rlp3.setMargins(10,10,10,11);
        init.setTextSize(15.0f);
        init.setBackground(gdAnimation2);
        init.setGravity(Gravity.CENTER);
        init.setLayoutParams(rlp3);
        init.setTextColor(Color.parseColor("#000000"));

        //Footer text
        LinearLayout linearLayout3 = new LinearLayout(this);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams5.addRule(12);
        linearLayout3.setLayoutParams(layoutParams5);
        linearLayout3.setOrientation(LinearLayout.VERTICAL);


        //Add views
        //    linearLayout2.addView(imageView);
        linearLayout2.addView(mail);
        linearLayout2.addView(pass);
        linearLayout2.addView(linearLayoutc);
        linearLayoutc.addView(rd);
        linearLayoutc.addView(rd2);
        linearLayout2.addView(init);
        linearLayout.addView(name);
        relativeLayout.addView(linearLayout);
      //  relativeLayout.addView(name);
        relativeLayout.addView(linearLayout2);

       // relativeLayout.addView(linearLayout);


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
                  //  prefs.write(USER, mail.getText().toString());
                   // prefs.write(PASS, pass.getText().toString());
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

    public void startAnimation() {
        final int start = Color.parseColor("#dd00820d");
        final int end = Color.parseColor("#dd000282");

        final ArgbEvaluator evaluator = new ArgbEvaluator();
        gdAnimation.setCornerRadius(0);
        gdAnimation.setOrientation(GradientDrawable.Orientation.TL_BR);
        final GradientDrawable gradient = gdAnimation;

        ValueAnimator animator = TimeAnimator.ofFloat(0.0f, 1.0f);
        animator.setDuration(10000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float fraction = valueAnimator.getAnimatedFraction();
                int newStrat = (int) evaluator.evaluate(fraction, start, end);
                int newEnd = (int) evaluator.evaluate(fraction, end, start);
                int[] newArray = {newStrat, newEnd};
                gradient.setColors(newArray);
            }
        });

        animator.start();
    }
    public void startAnimation2() {
        final int start2 = Color.parseColor("#ddFF0000");
        final int end2 = Color.parseColor("#ddFFA500");

        final ArgbEvaluator evaluator2 = new ArgbEvaluator();
        gdAnimation2.setCornerRadius(20);
        gdAnimation2.setOrientation(GradientDrawable.Orientation.TL_BR);
        final GradientDrawable gradient2 = gdAnimation2;

        ValueAnimator animator2 = TimeAnimator.ofFloat(0.0f, 1.0f);
        animator2.setDuration(10000);
        animator2.setRepeatCount(ValueAnimator.INFINITE);
        animator2.setRepeatMode(ValueAnimator.REVERSE);
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                Float fraction2 = valueAnimator2.getAnimatedFraction();
                int newStrat2 = (int) evaluator2.evaluate(fraction2, start2, end2);
                int newEnd2 = (int) evaluator2.evaluate(fraction2, end2, start2);
                int[] newArray2 = {newStrat2, newEnd2};
                gradient2.setColors(newArray2);
            }
        });

        animator2.start();
    }

    public void Dialog2() {
        new AlertDialog.Builder(this)
                .setTitle("Aviso!")
                .setMessage("" + Dialog())
                .setNegativeButton("OKAY!", null)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
				
						this.startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION",
														 Uri.parse("package:" + this.getPackageName())));
						Process.killProcess(Process.myPid());
					}
		
        loadLibrary("MyLib");
		Dialog2();
        SetupForm();
        startAnimation();
        startAnimation2();
        Intent intent = new Intent(this, Loader.class);
        startService(intent);
    }
}
