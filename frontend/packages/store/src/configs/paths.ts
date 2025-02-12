const PATH = {
  root: '/',
  dashboard: '/dashboard',
  management: '/management',
  payment: '/payment',
  setting: '/setting',
}

const paths = {
  root: PATH.root,
  main: `${PATH.dashboard}/total`,

  // auth
  auth: {
    signIn: '/sign-in',
    signUp: '/sign-up',
  },

  // dashboard
  dashboard: {
    root: PATH.dashboard,
    total: `${PATH.dashboard}/total`,

    sales: {
      root: `${PATH.dashboard}/sales`,
      menu: `${PATH.dashboard}/sales/menu`,
      company: `${PATH.dashboard}/sales/company`,
      time: `${PATH.dashboard}/sales/time`,
    },

    settlement: `${PATH.dashboard}/settlement`,
  },

  // management
  management: {
    root: PATH.management,
    menu: `${PATH.management}/menu`,
    payment: `${PATH.management}/payment`,
    contract: {
      root: `${PATH.management}/contract`,
      now: `${PATH.management}/contract/now`,
      new: `${PATH.management}/contract/new`,
      request: `${PATH.management}/contract/request`,
      history: `${PATH.management}/contract/history`,
    },
    settlement: {
      root: `${PATH.management}/settlement`,
      company: `${PATH.management}/settlement/company`,
      date: `${PATH.management}/settlement/date`,
    },
  },

  // payment
  payment: {
    root: PATH.payment,
    qr: `${PATH.payment}/qr`,
  },

  // setting
  setting: {
    root: PATH.setting,
    account: `${PATH.setting}/account`,
    app: `${PATH.setting}/app`,
  },
}

export default paths
