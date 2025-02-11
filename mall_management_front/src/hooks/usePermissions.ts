import useLoginStore from '@/stores/auth/login'

function usePermissions(permissionID: string) {
  const loginStore = useLoginStore()
  const { permissions } = loginStore

  return !!permissions.find((item) => item.includes(permissionID))
}

export default usePermissions
