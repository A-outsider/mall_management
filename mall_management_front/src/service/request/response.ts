export default interface NetResponse<T = null> {
  code: number
  message: string
  data: T
}
