// 送信ボタンを取得
const btn = document.getElementById("submit");
// 送信ボタンを初期状態で無効にする
//btn.disabled = true;
// メッセージ表示エリアを取得
const msg = document.getElementById("msg");
// 予約日付入力フィールドを取得
const date_plan = document.getElementById("date_plan");
// 予約日付入力時に実行されるリスナーを設定
date_plan.addEventListener('input', checkDay);
// 予約日付の初期値を現在の日付に設定
date_plan.value = new Date().toISOString().substring(0, 10);
// 内容入力フィールドを取得
const purpose = document.getElementById("purpose");
// 曜日表示フィールドを取得
const date_day = document.getElementById("date_day");
/**
 * 現在の日付に基づいて、曜日を計算し表示フィールドに設定する関数
 */
function setDay() { 
    // 曜日の日本語表記を配列で定義
    const dayJapanese = ["日", "月", "火", "水", "木", "金", "土"];
    // 選択された日付から曜日を計算
    const dayOfWeek = dayJapanese[new Date(date_plan.value).getDay()];
    // 曜日表示フィールドに設定
    date_day.value = dayOfWeek;
}
/**
 * 過去の選択日付と現在の日付を比較する関数 
 * @returns {boolean} 過去の日付が現在の日付以下である場合はtrue、それ以外の場合はfalseを返します。
 */
function compareDates() {
    // 現在の日付を取得してISOString形式に変換し、日付部分のみを抽出
    const d1 = new Date(new Date().toISOString().substring(0, 10));
    // 選択された日付をDateオブジェクトに変換
    const d2 = new Date(date_plan.value);
    // 比較して結果を返す
    return d1 <= d2;
}
// 予約日付の検証と表示更新を行う関数
function checkDay() {
    if (compareDates()) {//正しい場合
        msg.textContent = '　';
        setDay();
    } else {
        msg.textContent = '過去の日付を選択しないでください。';
        setDay();
    }
}
// 初期状態で曜日を表示
setDay();
//その他ボタンを取得
const sonota = document.getElementById("sonota")
//その他ボタンをクリック処理
sonota.addEventListener('click',()=>{
    document.getElementById("methodBox").innerHTML=`<input type="text" class="form-control" id="method" placeholder="面談方法を入力ください。" name="method" required>`;
})
