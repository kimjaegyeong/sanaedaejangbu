import dayjs from 'dayjs'
import axios from '@/configs/axios'
import Chart from 'react-apexcharts'
import { useState, useEffect } from 'react'
import { SelectDate } from '@/components/select/select-date'

import { Box, Card, Stack, Select, MenuItem, useTheme, FormControl } from '@mui/material'

import { Typography } from '@e201/ui' // 날짜 선택 컴포넌트 import
import { useTranslate } from '@/locales'

interface IDashboardPaymentResponse {
  year: number
  month: number
  totalAmount: number
}

interface IDashboardPaymentDailyResponse {
  year: number
  month: number
  day: number
  totalAmount: number
}

export default function TotalCompany() {
  const { t } = useTranslate('dashboard')
  const [data, setData] = useState<IDashboardPaymentResponse[]>([])
  const [dailyData, setDailyData] = useState<IDashboardPaymentDailyResponse[]>([])
  const [viewType, setViewType] = useState<'월별' | '일별'>('월별')
  const [categories, setCategories] = useState<string[]>([])
  const [seriesData, setSeriesData] = useState<number[]>([])
  const [loading, setLoading] = useState<boolean>(true)
  const [selectedYear, setSelectedYear] = useState<number>(dayjs().year())
  const [selectedMonth, setSelectedMonth] = useState<number>(dayjs().month() + 1)

  const theme = useTheme()

  const endDate = dayjs().format('YYYY-MM-DDTHH:mm:ss')
  const startDate = dayjs().startOf('month').format('YYYY-MM-DDTHH:mm:ss')

  // 데이터를 불러오는 useEffect (월별 데이터)
  useEffect(() => {
    const fetchData = async () => {
      setLoading(true)
      try {
        const response = await axios.get<IDashboardPaymentResponse[]>(
          '/companies/dashboards/years/months',
          {
            params: {
              startDate,
              endDate,
            },
          }
        )
        setData(response.data)
      } catch (error) {
        console.error('Error fetching data:', error)
      } finally {
        setLoading(false)
      }
    }

    if (viewType === '월별') {
      fetchData()
    }
  }, [startDate, endDate, viewType])

  // 데이터를 불러오는 useEffect (일별 데이터)
  useEffect(() => {
    const fetchDailyData = async () => {
      setLoading(true)
      const dailyStartDate = dayjs(`${selectedYear}-${String(selectedMonth).padStart(2, '0')}-01`)
        .startOf('month')
        .format('YYYY-MM-DDTHH:mm:ss')
      const dailyEndDate = dayjs(`${selectedYear}-${String(selectedMonth).padStart(2, '0')}-01`)
        .endOf('month')
        .format('YYYY-MM-DDTHH:mm:ss')

      try {
        const response = await axios.get<IDashboardPaymentDailyResponse[]>(
          '/companies/dashboards/years/days',
          {
            params: {
              startDate: dailyStartDate,
              endDate: dailyEndDate,
            },
          }
        )
        setDailyData(response.data)
      } catch (error) {
        console.error('Error fetching data:', error)
      } finally {
        setLoading(false)
      }
    }

    if (viewType === '일별') {
      fetchDailyData()
    }
  }, [selectedYear, selectedMonth, viewType])

  useEffect(() => {
    if (viewType === '월별') {
      const newCategories = data.map(
        (item) => `${item.year}-${String(item.month).padStart(2, '0')}`
      )
      const newSeriesData = data.map((item) => item.totalAmount)

      setCategories(newCategories)
      setSeriesData(newSeriesData)
    }

    if (viewType === '일별') {
      const newCategories = dailyData.map(
        (item) =>
          `${item.year}-${String(item.month).padStart(2, '0')}-${String(item.day).padStart(2, '0')}`
      )
      const newSeriesData = dailyData.map((item) => item.totalAmount)

      setCategories(newCategories)
      setSeriesData(newSeriesData)
    }
  }, [data, dailyData, viewType])

  const chartOptions: ApexCharts.ApexOptions = {
    chart: {
      type: 'line',
      zoom: {
        enabled: true,
        autoScaleYaxis: true,
      },
      toolbar: {
        show: false,
      },
    },
    colors: [theme.palette.primary.main],
    xaxis: {
      categories,
      labels: {
        formatter(value: string) {
          return dayjs(value).format(viewType === '월별' ? 'YYYY/MM' : 'YYYY/MM/DD')
        },
        style: {
          colors:
            theme.palette.mode === 'light' ? theme.palette.grey[800] : theme.palette.grey[400],
        },
      },
    },
    yaxis: {
      min: 0,
      labels: {
        formatter(value: number) {
          return value === 0 ? '' : `${value.toLocaleString()}`
        },
        style: {
          colors:
            theme.palette.mode === 'light' ? theme.palette.grey[800] : theme.palette.grey[400],
        },
      },
    },
    stroke: {
      curve: 'smooth',
      width: 3,
    },
    grid: {
      strokeDashArray: 3,
    },
    tooltip: {
      theme: theme.palette.mode === 'light' ? 'light' : 'dark',
      y: {
        formatter(value: number) {
          return `${value.toLocaleString()}원`
        },
      },
    },
  }

  const chartSeries = [
    {
      data: seriesData,
      name: 'Total Amount',
    },
  ]

  const handleDateChange = (year: number, month: number) => {
    setSelectedYear(year)
    setSelectedMonth(month)
  }

  return (
    <Card
      sx={{
        backdropFilter: 'blur(10px)',
        backgroundColor: 'rgba(255, 255, 255, 0.1)',
        borderRadius: '16px',
        boxShadow: '0 2px 15px rgba(0, 0, 0, 0.1)',
        height: '280px',
      }}
    >
      <Stack p={1} sx={{ position: 'relative' }} spacing={1}>
        <Box display="flex" alignItems="center" sx={{ position: 'relative' }}>
          {/* 좌측에 SelectDate 고정 (일별/월별 상관없이 항상 표시) */}
          {viewType === '일별' && (
            <Box sx={{ position: 'absolute', left: 2, top: 2 }}>
              <SelectDate
                year={selectedYear}
                month={selectedMonth}
                t={t}
                onChange={handleDateChange}
              />
            </Box>
          )}

          {/* 중앙에 Typography 고정 */}
          <Typography variant="h6" sx={{ margin: '0 auto', textAlign: 'center', pt: 1 }}>
            {viewType === '월별' ? '월별 총액' : '일별 총액'}
          </Typography>

          {/* 우측에 월별/일별 선택 박스 고정 */}
          <FormControl
            variant="outlined"
            size="small"
            sx={{ position: 'absolute', right: 2, minWidth: 120, top: 2 }}
          >
            <Select
              value={viewType}
              onChange={(e) => setViewType(e.target.value as '월별' | '일별')}
            >
              <MenuItem value="월별">월별</MenuItem>
              <MenuItem value="일별">일별</MenuItem>
            </Select>
          </FormControl>
        </Box>

        <Box height={150}>
          {seriesData.length > 0 ? (
            <Chart options={chartOptions} series={chartSeries} type="line" height={200} />
          ) : (
            <Typography
              variant="h6"
              sx={{
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                height: '100%',
              }}
            >
              No Data
            </Typography>
          )}
        </Box>
      </Stack>
    </Card>
  )
}
