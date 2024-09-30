import ExcelJS from 'exceljs'
import { useTranslate } from '@/locales'
import React, { useRef, useState, useEffect } from 'react'

import {
  Paper,
  Table,
  Stack,
  Button,
  Tooltip,
  TableRow,
  Checkbox,
  Collapse,
  TableBody,
  TableCell,
  TableHead,
  TextField,
  Typography,
  IconButton,
  TableContainer,
} from '@mui/material'

import { Iconify } from '@e201/ui'

import MemberCreateButton from './member-create-button'

interface RowData {
  사번: string
  이름: string
}

export default function EmployeeCreate() {
  const [empData, setEmpData] = useState<RowData[]>([])
  const [newRow, setNewRow] = useState<RowData>({ 사번: '', 이름: '' })
  const [editIndex, setEditIndex] = useState<number | null>(null)
  const [selectedRows, setSelectedRows] = useState<number[]>([])

  const messageEndRef = useRef<HTMLTableRowElement>(null)

  const { t } = useTranslate('member')
  useEffect(() => {
    if (messageEndRef.current) {
      messageEndRef.current.scrollIntoView({ behavior: 'smooth' })
    }
  }, [empData])

  const handleFileUpload = async (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0]
    const workbook = new ExcelJS.Workbook()
    const reader = new FileReader()

    reader.onload = async () => {
      const buffer = reader.result as ArrayBuffer
      await workbook.xlsx.load(buffer)
      const worksheet = workbook.worksheets[0]

      const headerRow = worksheet.getRow(1).values
      const headers = Array.isArray(headerRow) ? headerRow : []

      const indexOfEmpNo = headers.indexOf('사번')
      const indexOfName = headers.indexOf('이름')

      if (indexOfEmpNo === -1 || indexOfName === -1) {
        return
      }

      const newData: RowData[] = []
      for (let i = 2; i <= worksheet.rowCount; i++) {
        const row = worksheet.getRow(i)
        const rowData: RowData = {
          사번: row.getCell(indexOfEmpNo).value as string,
          이름: row.getCell(indexOfName).value as string,
        }
        newData.push(rowData)
      }

      setEmpData((prev) => [...prev, ...newData])
    }

    if (file) {
      reader.readAsArrayBuffer(file)
    }
  }

  const handleAddRow = () => {
    if (newRow.사번 && newRow.이름) {
      setEmpData((prev) => [...prev, newRow])
      setNewRow({ 사번: '', 이름: '' })
    }
  }

  const handleEditRow = (index: number) => {
    setEditIndex(index)
  }

  const handleSaveEdit = () => {
    setEditIndex(null)
  }

  const handleDeleteSelectedRows = () => {
    setEmpData(empData.filter((_, index) => !selectedRows.includes(index)))
    setSelectedRows([])
  }

  return (
    <Stack sx={{ maxHeight: '85vh', overflow: 'hidden' }}>
      <Stack direction="row" justifyContent="space-between" pb={3}>
        <Typography variant="h3" fontWeight={800}>
          {t('create_employee')}
        </Typography>
        <MemberCreateButton onFileUpload={handleFileUpload} />

        {/* {selectedRows.length > 0 && (
          <Button variant="outlined" color="error" onClick={handleDeleteSelectedRows}>
            선택 항목 삭제
          </Button>
        )} */}
      </Stack>
      <Collapse in={selectedRows.length > 0}>
        <Stack
          width={1}
          height={57}
          direction="row"
          justifyContent="space-evenly"
          alignItems="center"
          px={2}
          py={1}
          zIndex={1}
        >
          <Typography variant="subtitle1">
            {selectedRows.length}
            {t('selected')}
          </Typography>
          <Stack direction="row" spacing={1} alignItems="center">
            <Tooltip title={t('create_account')} arrow disableInteractive>
              <IconButton color="success">
                <Iconify icon="ri:user-add-line" />
              </IconButton>
            </Tooltip>
            <Tooltip title={t('delete')} arrow disableInteractive>
              <IconButton color="error" onClick={handleDeleteSelectedRows}>
                <Iconify icon="solar:trash-bin-minimalistic-2-linear" />
              </IconButton>
            </Tooltip>
          </Stack>
        </Stack>
      </Collapse>
      <TableContainer
        sx={{
          overflowY: 'auto',
          borderBottom: (theme) => `1px solid ${theme.palette.divider}`,
        }}
      >
        <Paper>
          <Table size="small" stickyHeader>
            <TableHead>
              <TableRow>
                <TableCell align="center">
                  <Checkbox
                    checked={selectedRows.length === empData.length && empData.length > 0}
                    indeterminate={selectedRows.length > 0 && selectedRows.length < empData.length}
                    onChange={(e) => {
                      if (e.target.checked) {
                        setSelectedRows(empData.map((_, index) => index)) // 모든 행 선택
                      } else {
                        setSelectedRows([]) // 선택 해제
                      }
                    }}
                  />
                </TableCell>
                <TableCell align="center">{t('sequence_number')}</TableCell>
                <TableCell>{t('employee_number')}</TableCell>
                <TableCell>{t('name')}</TableCell>
                <TableCell />
              </TableRow>
            </TableHead>
            <TableBody>
              {empData.map((row, index) => (
                <TableRow key={index}>
                  <TableCell align="center">
                    <Checkbox
                      checked={selectedRows.includes(index)}
                      onChange={(e) => {
                        if (e.target.checked) {
                          setSelectedRows((prev) => [...prev, index])
                        } else {
                          setSelectedRows((prev) => prev.filter((i) => i !== index))
                        }
                      }}
                    />
                  </TableCell>
                  <TableCell align="center">{index + 1}</TableCell>
                  <TableCell>
                    {editIndex === index ? (
                      <TextField
                        value={row.사번}
                        onChange={(e) =>
                          setEmpData((prev) =>
                            prev.map((r, i) => (i === index ? { ...r, 사번: e.target.value } : r))
                          )
                        }
                        variant="standard"
                        size="small"
                      />
                    ) : (
                      <Typography onClick={() => handleEditRow(index)}>{row.사번}</Typography>
                    )}
                  </TableCell>
                  <TableCell>
                    {editIndex === index ? (
                      <TextField
                        value={row.이름}
                        onChange={(e) =>
                          setEmpData((prev) =>
                            prev.map((r, i) => (i === index ? { ...r, 이름: e.target.value } : r))
                          )
                        }
                        variant="standard"
                        size="small"
                      />
                    ) : (
                      <Typography onClick={() => handleEditRow(index)}>{row.이름}</Typography>
                    )}
                  </TableCell>
                  <TableCell>
                    {editIndex === index ? (
                      <Button onClick={handleSaveEdit} size="small" variant="contained">
                        {t('save')}
                      </Button>
                    ) : null}
                  </TableCell>
                </TableRow>
              ))}
              {/* 새로운 직원 추가 */}
              <TableRow ref={messageEndRef}>
                <TableCell />
                <TableCell align="center">{empData.length + 1}</TableCell>
                <TableCell>
                  <TextField
                    placeholder={t('employee_number')}
                    variant="standard"
                    size="small"
                    value={newRow.사번}
                    onChange={(e) => setNewRow({ ...newRow, 사번: e.target.value })}
                    sx={{ py: 1 }}
                  />
                </TableCell>
                <TableCell>
                  <TextField
                    placeholder={t('name')}
                    variant="standard"
                    size="small"
                    value={newRow.이름}
                    onChange={(e) => setNewRow({ ...newRow, 이름: e.target.value })}
                    sx={{ py: 1 }}
                  />
                </TableCell>

                <TableCell>
                  <Button variant="outlined" size="small" onClick={handleAddRow}>
                    {t('add')}
                  </Button>
                </TableCell>
              </TableRow>
            </TableBody>
          </Table>
        </Paper>
      </TableContainer>
    </Stack>
  )
}