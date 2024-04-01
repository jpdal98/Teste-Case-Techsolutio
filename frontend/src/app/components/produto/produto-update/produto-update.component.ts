import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Produto } from 'src/app/models/produto';
import { ProdutoService } from 'src/app/services/produto.service';

@Component({
  selector: 'app-produto-update',
  templateUrl: './produto-update.component.html',
  styleUrls: ['./produto-update.component.css']
})
export class ProdutoUpdateComponent implements OnInit {

  produto: Produto = {
    id:         '',
    name:       '',
    supplier:   '',
    price:      ''
  }

  name: FormControl = new FormControl(null, Validators.minLength(5));
  supplier: FormControl = new FormControl(null, Validators.minLength(4));
  price: FormControl = new FormControl(null, Validators.required);

  constructor(
    private service: ProdutoService,
    private toast: ToastrService,
    private router: Router,
    private route: ActivatedRoute,
    ) { }

  ngOnInit(): void {
    this.produto.id = this.route.snapshot.paramMap.get('id');
    this.findById();
   }

  findById(): void {
    this.service.findById(this.produto.id).subscribe(resposta => {
      this.produto = resposta;
    })
  }

  update(): void {
    this.service.update(this.produto).subscribe(() => {
      this.toast.success('Produto atualizado com sucesso', 'Update');
      this.router.navigate(['produtos'])
    }, ex => {
      if(ex.error.errors) {
        ex.error.errors.forEach(element => {
          this.toast.error(element.message);
        });
      } else {
        this.toast.error(ex.error.message);
      }
    })
  }
  
  validaCampos(): boolean {
    return this.name.valid && this.supplier.valid && this.price.valid
  }
}
