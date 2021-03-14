CREATE OR REPLACE FUNCTION subtrairData(minhaData in DATE, diferencaDia in number)
return DATE
AS
begin
    return minhaData - NUMTODSINTERVAL(diferencaDia, 'day');
end;