// 内容テーブルを取得
const employeeTable = document.getElementById("employee");
//担当者配列
let employeeTableHTML ='' 
// 内容情報のHTMLを生成する変数
let employeeData;
// データを取得する関数
function fetchDataemployee(url) {
  // Fetch APIを使用してデータを取得
  fetch("/kk/office/employeepai")
    .then(response => {
      // レスポンスが成功した場合、JSONデータを解析
      if (!response.ok) {
        throw new Error('ネットワークエラー');
      }
      return response.json();
    })
    .then(data => {
      // データをコンソールに表示
      employeeData = data;
      employeeData.map((e) => {
      employeeTableHTML += `<tr><td>${e.id}</td><td>${e.tableName}</td><td>${e.status}</td><td>${e.plan_end==null?"-":getStyleDate(e.plan_end)}</td><td>${e.date_start == null && e.status !="現場"?"稼動中":e.date_start != null?getStyleDate(e.date_start):"-"}</td><td><button type="button" class="btn btn-outline-secondary" onclick="editemployee('${e.id}','${e.tableName}','${e.status}','${e.plan_end}','${e.date_start}','employee');">編集</button>・${e.status !== "削除" ?  `<button type="button" class="btn btn-outline-warning" onclick="deleteemployee('${e.id}','${e.tableName}','employee');">削除</button>` : `<button type="button" class="btn btn-outline-primary" onclick="restoreemployee('${e.id}','${e.tableName}','employee');">復元</button>` }</td></tr>`;
      }); employeeTable.innerHTML = employeeTableHTML;
    })
    .catch(error => {
      // エラーが発生した場合、エラーメッセージを表示
      console.error('エラー:', error);
    });
}
fetchDataemployee();
function getStyleDate(str){
  return `${str.substring(0, 4)}年${str.substring(5, 7)}月${str.substring(8, 10)}日`;
}
