import type { IContract } from '@/types/contract'
import type { GridColDef, GridRowParams, GridRowSelectionModel } from '@mui/x-data-grid'

import dayjs from 'dayjs'
import api from '@/configs/api'
import { useState } from 'react'
import paths from '@/configs/paths'
import axios from '@/configs/axios'
import { useQuery } from '@tanstack/react-query'
import { Breadcrumbs } from '@/components/breadcrumbs'

import { DataGrid } from '@mui/x-data-grid'
import {
  Box,
  Card,
  Stack,
  Button,
  Tooltip,
  lighten,
  Collapse,
  TextField,
  IconButton,
} from '@mui/material'

import { Iconify, Typography } from '@e201/ui'

export default function ContractRequestManagementView() {
  const [selected, setSelected] = useState<GridRowSelectionModel>([])

  const queryFn = async () => {
    const response = await axios.get<IContract[]>(api.contract.list)
    return response.data
  }

  const { data, isPending, isError } = useQuery({ queryKey: [api.contract.list], queryFn })

  const columns: GridColDef[] = [
    {
      field: 'name',
      headerName: '회사명',
      flex: 1,
      minWidth: 150,
    },
    { field: 'email', headerName: '이메일', width: 200 },
    { field: 'phone', headerName: '대표번호', width: 150, resizable: false },
    {
      field: 'createdAt',
      type: 'date',
      headerName: '신청날짜',
      resizable: false,
      width: 120,
      valueFormatter: (value: Date) => dayjs(value).format('YYYY-MM-DD'),
    },
    {
      field: 'action',
      type: 'actions',
      headerName: '처리',
      align: 'left',
      resizable: false,
      getActions: (params: GridRowParams) => [
        <Tooltip title="수락" arrow disableInteractive>
          <IconButton color="success">
            <Iconify icon="iconamoon:check-bold" />
          </IconButton>
        </Tooltip>,
        <Tooltip title="거절" arrow disableInteractive>
          <IconButton color="error">
            <Iconify icon="gravity-ui:xmark" />
          </IconButton>
        </Tooltip>,
      ],
    },
  ]

  return (
    <Box>
      <Breadcrumbs
        title="계약 요청"
        routes={[
          { title: '관리', path: paths.management.menu },
          { title: '계약 관리', path: paths.management.contract.root },
          { title: '계약 요청' },
        ]}
        action={
          <Button>
            <Iconify icon="ic:round-plus" />
            <Typography variant="subtitle2" pl={0.5}>
              계약 추가
            </Typography>
          </Button>
        }
      />

      <Card>
        <Stack
          direction="row"
          justifyContent="space-between"
          alignItems="center"
          p={1}
          sx={{ borderBottom: (theme) => `1px solid ${theme.palette.divider}` }}
        >
          <Stack width={1} direction="row" alignItems="center" spacing={1}>
            <TextField size="small" label="search" fullWidth />
          </Stack>
        </Stack>

        <Collapse in={selected.length > 0}>
          <Stack
            width={1}
            height={57}
            direction="row"
            justifyContent="space-between"
            alignItems="center"
            px={2}
            py={1}
            zIndex={1}
            sx={{ borderBottom: (theme) => `1px solid ${theme.palette.divider}` }}
          >
            <Typography variant="subtitle1">{selected.length} selected</Typography>
            <Stack direction="row" spacing={1} alignItems="center">
              <Tooltip title="일괄 수락" arrow disableInteractive>
                <IconButton color="success">
                  <Iconify icon="iconamoon:check-bold" />
                </IconButton>
              </Tooltip>
              <Tooltip title="일괄 거절" arrow disableInteractive>
                <IconButton color="error">
                  <Iconify icon="gravity-ui:xmark" />
                </IconButton>
              </Tooltip>
            </Stack>
          </Stack>
        </Collapse>

        <DataGrid
          columns={columns}
          rows={data}
          rowSelectionModel={selected}
          onRowSelectionModelChange={setSelected}
          checkboxSelection
          hideFooter
          hideFooterPagination
          disableColumnSorting
          disableColumnFilter
          disableColumnMenu
          disableRowSelectionOnClick
          loading={isPending}
          slotProps={{
            noRowsOverlay: {},
            noResultsOverlay: {},
          }}
          sx={{
            height: 500,
            '& .MuiDataGrid-columnSeparator': {
              color: 'transparent',
              ':hover': {
                color: (theme) => theme.palette.divider,
              },
            },
            '& .MuiDataGrid-cell:focus-within': { outline: 'none' },
            '& .MuiDataGrid-columnHeader:focus-within': { outline: 'none' },
            '.MuiDataGrid-columnHeaders': {
              borderBottom: (theme) => `1px solid ${theme.palette.divider}`,
            },
          }}
        />
      </Card>
    </Box>
  )
}