import type { ILicenseOcr } from '@/types/ocr'
import type { ISignUpRequest } from '@/types/sign-up'

import dayjs from 'dayjs'
import api from '@/configs/api'
import axios from '@/configs/axios'
import { useTranslate } from '@/locales'
import { useForm } from 'react-hook-form'
import { useMemo, useState } from 'react'
import { parseDateString } from '@/utils/date'
import { useQuery } from '@tanstack/react-query'
import BusinessLicenseForm from '@/sections/sign-up/business-license-form'

import { Stack, Button, useTheme, Typography, useMediaQuery } from '@mui/material'

import { Upload, Iconify, FormInput } from '@e201/ui'

interface IForm {
  email: string
  password: string
  passwordConfirm: string
  phone: string
  businessName: string
  repName: string
  address: string
  registerNumber: string
  businessType: string
  openDate: string
}

interface IProps {
  onNext: () => void
}

export default function SignUpFormView({ onNext }: IProps) {
  const { t } = useTranslate('sign-up')

  const { breakpoints } = useTheme()
  const stackDirection = useMediaQuery(breakpoints.up('md')) ? 'row' : 'column'

  const [file, setFile] = useState<File | null>(null)

  const formMethod = useForm<IForm>({
    mode: 'onSubmit',
    defaultValues: {
      email: '',
      password: '',
      passwordConfirm: '',
      phone: '',
      businessName: '',
      repName: '',
      address: '',
      registerNumber: '',
      businessType: '',
      openDate: '',
    },
  })
  const { control, watch } = formMethod

  const queryFn = async () => {
    const formData = new FormData()
    if (!file) {
      return undefined
    }
    formData.append('image', file)

    const response = await axios.post<ILicenseOcr>(api.common.ocr, formData, {
      headers: {
        'Content-Type': 'multipart/formdata',
      },
    })
    return response.data
  }

  const {
    data: licenseData,
    isPending,
    isError,
  } = useQuery({
    queryKey: [api.common.ocr, file],
    queryFn,
    enabled: !!file,
    staleTime: Infinity,
    gcTime: Infinity,
    retry: 1,
  })

  const fileChangeHandler = (files: File[]) => {
    if (files.length === 0) {
      setFile(null)
      return
    }
    setFile(files[0])
  }

  const submitHandler = async (form: IForm) => {
    if (!licenseData) {
      return
    }

    const body: ISignUpRequest = {
      email: form.email,
      password: form.password,
      passwordConfirm: form.passwordConfirm,
      phone: form.phone,
      address: licenseData.address,
      businessName: licenseData.businessName,
      businessType: licenseData.businessType,
      repName: licenseData.repName,
      registerNumber: licenseData.registerNumber,
      openDate: dayjs(parseDateString(licenseData.openDate)).format(),
    }

    try {
      const res = await axios.post(api.auth.signUp, body)
      console.log(res)
      onNext()
    } catch (error) {
      console.error(error)
    }
  }

  const BusinessLicenseFormRender = useMemo(() => {
    if (!file) {
      return null
    }
    if (isError) {
      return (
        <Stack
          width={1}
          height={200}
          justifyContent="center"
          alignItems="center"
          borderRadius={1}
          spacing={1}
          sx={{
            border: (theme) => `1px solid ${theme.palette.divider}`,
            color: (theme) => theme.palette.text.secondary,
          }}
        >
          <Iconify icon="fluent:document-error-24-filled" width={40} />
          <Typography fontSize={14} fontWeight={400}>
            {t('error.ocr')}
          </Typography>
        </Stack>
      )
    }
    return <BusinessLicenseForm isPending={isPending} license={licenseData} />
  }, [file, isError, isPending, licenseData, t])

  return (
    <form onSubmit={formMethod.handleSubmit(submitHandler)} noValidate>
      <Stack spacing={4}>
        <Stack spacing={4} direction={stackDirection}>
          <Stack spacing={2} width={1} sx={{ mb: 2 }}>
            <Typography variant="subtitle1" sx={{ pl: 1 }}>
              {t('title.user')}
            </Typography>
            <FormInput
              name="email"
              control={control}
              rules={{
                required: t('validate.email.required'),
                pattern: {
                  value: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
                  message: t('validate.email.pattern'),
                },
              }}
              label={t('form.email')}
              type="email"
            />
            <FormInput
              name="password"
              control={control}
              rules={{
                required: t('validate.password.required'),
                minLength: {
                  value: 8,
                  message: t('validate.password.minLength'),
                },
              }}
              label={t('form.password')}
              type="password"
            />
            <FormInput
              name="passwordConfirm"
              control={control}
              rules={{
                required: t('validate.password_confirm.validate'),
                validate: {
                  confirm: (value) =>
                    value === watch('password') || t('validate.password_confirm.validate'),
                },
              }}
              label={t('form.password_confirm')}
              type="password"
            />
            <FormInput
              name="phone"
              control={control}
              rules={{
                required: t('validate.phone.required'),
                pattern: {
                  value: /^(\d{2,3})[-.\s]?(\d{3,4})[-.\s]?(\d{4})$/,
                  message: t('validate.phone.pattern'),
                },
              }}
              label={t('form.phone')}
              type="tel"
            />
          </Stack>

          {/* <Stack spacing={2} width={1} sx={{ mb: 2 }}>
            <Typography variant="subtitle1" sx={{ pl: 1 }}>
              {t('title.account')}
            </Typography>
            <FormInput name="bank" control={control} label={t('form.bank')} select>
              {BankDataOptions}
            </FormInput>
            <FormInput name="account" control={control} label={t('form.account')} />
          </Stack> */}
        </Stack>

        <Stack spacing={2} width={1} sx={{ mb: 2 }}>
          <Typography variant="subtitle1" sx={{ pl: 1 }}>
            {t('title.business')}
          </Typography>
          <Upload placeholder={t('form.upload')} onChange={fileChangeHandler} />
          {BusinessLicenseFormRender}
        </Stack>

        <Button type="submit">{t('button.next')}</Button>
      </Stack>
    </form>
  )
}
