import term from '@/assets/data/term'
import { useTranslate } from '@/locales'

import { Dialog, Button, DialogTitle, DialogContent, DialogActions } from '@mui/material'

import { Typography } from '@e201/ui'

export default function PrivacyDialog({ open, onClose }: { open: boolean; onClose: () => void }) {
  const { t } = useTranslate('microsite')
  return (
    <Dialog open={open} onClose={onClose} maxWidth="sm" fullWidth>
      <DialogTitle>{t('final.privacy')}</DialogTitle>
      <DialogContent>
        <Typography variant="body1" whiteSpace="pre-wrap">
          {term.personal}
        </Typography>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary">
          {t('close')}
        </Button>
      </DialogActions>
    </Dialog>
  )
}