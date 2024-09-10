Transaction Validator API

A Transaction Validator API é uma aplicação desenvolvida em Java usando Spring Boot para validar e autorizar transações com cartões de crédito. 
A API processa transações com base nas informações de MCC (Merchant Category Code) e verifica se há saldo suficiente nas categorias apropriadas. 
Caso não haja saldo suficiente na categoria específica, a API pode recorrer a uma categoria de "CASH" como fallback.

Funcionalidades

- Receber Transações: Processa payload JSON para transações de cartão de crédito.
- Mapeamento MCC: Usa o MCC para determinar a categoria de saldo a ser usada.
- Verificação de Saldo: Aprova ou rejeita a transação com base na disponibilidade de saldo.
- Fallback para CASH: Se a transação não puder ser coberta pela categoria principal, verifica o saldo em CASH.

Endpoints

Autorização de Transação

- URL: `/api/transaction`
- Método: `POST`
- Descrição: Recebe uma transação e valida se pode ser aprovada com base no saldo disponível.

Payload de Requisição

json exemplo:
{
    "account": "123",
    "totalAmount": 100.00,
    "mcc": "5811",
    "merchant": "PADARIA DO ZE               SAO PAULO BR"
}
