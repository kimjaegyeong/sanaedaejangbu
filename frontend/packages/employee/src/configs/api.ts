export const BASE_URL = 'https://sanedaejangbu.site/api'

const api = {
  qr: '/employees/qr',

  employeeMeal: (id: string) => `/employees/${id}/meals`, // 식대 사용 현황 및 월 식대 조회 API
  storesList: '/employees/stores', // 제휴 식당 리스트 조회 API
  validationId: '/employees/qr', // QR 코드 uuid POST API
  login: '/employees/auth', // 로그인, 로그아웃 API
  changePw: (id: string) => `/employees/${id}/password`, // 비밀번호 변경 API
  user: (id: string) => `/employees/${id}`, // 직원 정보 조회 API
  payments: (id: string, start: string, end: string) =>
    `/employees/${id}/menus?start=${start}&end=${end}`, // 식사 기록 조회 API

  auth: {
    login: 'companies/employees/auth',
  },
}

export default api
