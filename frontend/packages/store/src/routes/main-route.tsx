import type { RouteObject } from 'react-router-dom'

import paths from '@/configs/paths'
import AppLayout from '@/layouts/app-layout'
import SignInView from '@/pages/auth/sign-in-view'
import SignUpView from '@/pages/auth/sign-up-view'
import SignInLayout from '@/layouts/sign-in-layout'
import SignUpLayout from '@/layouts/sign-up-layout'
import PaymentLayout from '@/layouts/payment-layout'
import QrPaymentView from '@/pages/payment/qr-payment-view'
import TotalDashboardView from '@/pages/dashboard/total-dashboard-view'
import MenuManagementView from '@/pages/management/menu-management-view'
import SettlementDateView from '@/pages/management/settlement-date-view'
import SettlementCompanyView from '@/pages/management/settlement-company-view'
import SalesMenuDashboardView from '@/pages/dashboard/sales-menu-dashboard-view'
import SalesTimeDashboardView from '@/pages/dashboard/sales-time-dashboard-view'
import SettlementDashboardView from '@/pages/dashboard/settlement-dashboard-view'
import { PaymentManagementView } from '@/pages/management/payment-management-view'
import SalesCompanyDashboardView from '@/pages/dashboard/sales-company-dashboard-view'
import ContractNowManagementView from '@/pages/management/contract-now-management-view'
import ContractNewManagementView from '@/pages/management/contract-new-management-view'
import ContractRequestManagementView from '@/pages/management/contract-request-management-view'
import ContractHistoryManagementView from '@/pages/management/contract-history-management-view'

const mainRoute: RouteObject[] = [
  {
    element: <SignInLayout />,
    children: [
      {
        path: paths.auth.signIn,
        element: <SignInView />,
      },
    ],
  },
  {
    element: <SignUpLayout />,
    children: [
      {
        path: paths.auth.signUp,
        element: <SignUpView />,
      },
    ],
  },
  {
    element: <AppLayout />,
    children: [
      // Dashboard
      {
        path: paths.dashboard.total,
        element: <TotalDashboardView />,
      },
      {
        path: paths.dashboard.sales.menu,
        element: <SalesMenuDashboardView />,
      },
      {
        path: paths.dashboard.sales.company,
        element: <SalesCompanyDashboardView />,
      },
      {
        path: paths.dashboard.sales.time,
        element: <SalesTimeDashboardView />,
      },
      {
        path: paths.dashboard.settlement,
        element: <SettlementDashboardView />,
      },

      // Management
      {
        path: paths.management.menu,
        element: <MenuManagementView />,
      },
      {
        path: paths.management.payment,
        element: <PaymentManagementView />,
      },

      // Management - Contract
      {
        path: paths.management.contract.now,
        element: <ContractNowManagementView />,
      },
      {
        path: paths.management.contract.new,
        element: <ContractNewManagementView />,
      },
      {
        path: paths.management.contract.request,
        element: <ContractRequestManagementView />,
      },
      {
        path: paths.management.contract.history,
        element: <ContractHistoryManagementView />,
      },
      {
        path: paths.management.contract.history,
        element: <ContractHistoryManagementView />,
      },

      // Management - Settlement
      {
        path: paths.management.settlement.company,
        element: <SettlementCompanyView />,
      },
      {
        path: paths.management.settlement.date,
        element: <SettlementDateView />,
      },
    ],
  },
  {
    element: <PaymentLayout />,
    children: [
      // Payment
      {
        path: paths.payment.qr,
        element: <QrPaymentView />,
      },
    ],
  },
]

export default mainRoute
