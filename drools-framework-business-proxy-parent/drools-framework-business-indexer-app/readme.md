docker run -p 5000:5000 elastichq/elasticsearch-hq


PUT droolstransaction/_settings
{
  "index.mapping.total_fields.limit": 200000
}

PUT droolsaction/_settings
{
   "index.mapping.total_fields.limit": 200000
}