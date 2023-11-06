//get tinh
const options = {
  method: "GET",
  headers: {
    "Content-Type": "application/json",
    token: "234a71c7-7b2c-11ee-af43-6ead57e9219a",
  },
};

fetch(
  "https://online-gateway.ghn.vn/shiip/public-api/master-data/province",
  options
)
  .then((res) => res.json())
  .then((data) => console.log(data))
  .catch((error) => console.error("Error:", error));
//gethuyen
const prodcode = 269;
const optionstogethuyen = {
  method: "POST",
  headers: {
    "Content-Type": "application/json",
    token: "234a71c7-7b2c-11ee-af43-6ead57e9219a",
  },
  body: JSON.stringify({ district_id: prodcode }),
};
fetch(
  "https://online-gateway.ghn.vn/shiip/public-api/master-data/ward",
  optionstogethuyen
)
  .then((res) => res.json())
  .then((data) => console.log(data))
  .catch((error) => console.error("Error:", error));

//get lấy thông tin Phường, Xã
const districtID = 2264;
const getphuongxa = {
  method: "POST",
  headers: {
    "Content-Type": "application/json",
    token: "234a71c7-7b2c-11ee-af43-6ead57e9219a",
  },
  body: JSON.stringify({ district_id: districtID }),
};
fetch(
  "https://online-gateway.ghn.vn/shiip/public-api/master-data/ward",
  getphuongxa
)
  .then((res) => res.json())
  .then((data) => console.log(data))
  .catch((error) => console.error("Error:", error));

//get lấy thông tin Phường, Xã
const districtto = 2264;
const districtform = 2264;
const getDichVu = {
  method: "POST",
  headers: {
    "Content-Type": "application/json",
    token: "234a71c7-7b2c-11ee-af43-6ead57e9219a",
  },
  body: JSON.stringify({
    to_district: districtto,
    shop_id: 4676018,
    from_district: districtform,
  }),
};
fetch(
  "https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/available-services",
  getDichVu
)
  .then((res) => res.json())
  .then((data) => console.log(data))
  .catch((error) => console.error("Error:", error));

//get get giatien
const to_district_ids = 1444;
const from_district_ids = 1542;
const to_ward_codes = "20314";
const service_ids = 53321;
const heights = 15;
const lengths = 15;
const weights = 1000;
const widths = 11;
const getGiaTien = {
  method: "POST",
  headers: {
    "Content-Type": "application/json",
    token: "234a71c7-7b2c-11ee-af43-6ead57e9219a",
    shop_id: "234a71c7-7b2c-11ee-af43-6ead57e9219a",
  },
  body: JSON.stringify({
    service_id: service_ids,
    insurance_value: 500000,
    coupon: null,
    from_district_id: from_district_ids,
    to_district_id: to_district_ids,
    to_ward_code: to_ward_codes,
    height: heights,
    length: lengths,
    weight: weights,
    width: widths,
  }),
};
fetch(
  "https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee",
  getGiaTien
)
  .then((res) => res.json())
  .then((data) => console.log(data))
  .catch((error) => console.error("Error:", error));
