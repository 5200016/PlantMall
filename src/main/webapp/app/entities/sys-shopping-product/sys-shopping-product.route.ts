import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysShoppingProduct } from 'app/shared/model/sys-shopping-product.model';
import { SysShoppingProductService } from './sys-shopping-product.service';
import { SysShoppingProductComponent } from './sys-shopping-product.component';
import { SysShoppingProductDetailComponent } from './sys-shopping-product-detail.component';
import { SysShoppingProductUpdateComponent } from './sys-shopping-product-update.component';
import { SysShoppingProductDeletePopupComponent } from './sys-shopping-product-delete-dialog.component';
import { ISysShoppingProduct } from 'app/shared/model/sys-shopping-product.model';

@Injectable({ providedIn: 'root' })
export class SysShoppingProductResolve implements Resolve<ISysShoppingProduct> {
    constructor(private service: SysShoppingProductService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysShoppingProduct> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysShoppingProduct>) => response.ok),
                map((sysShoppingProduct: HttpResponse<SysShoppingProduct>) => sysShoppingProduct.body)
            );
        }
        return of(new SysShoppingProduct());
    }
}

export const sysShoppingProductRoute: Routes = [
    {
        path: 'sys-shopping-product',
        component: SysShoppingProductComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysShoppingProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-shopping-product/:id/view',
        component: SysShoppingProductDetailComponent,
        resolve: {
            sysShoppingProduct: SysShoppingProductResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysShoppingProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-shopping-product/new',
        component: SysShoppingProductUpdateComponent,
        resolve: {
            sysShoppingProduct: SysShoppingProductResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysShoppingProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-shopping-product/:id/edit',
        component: SysShoppingProductUpdateComponent,
        resolve: {
            sysShoppingProduct: SysShoppingProductResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysShoppingProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysShoppingProductPopupRoute: Routes = [
    {
        path: 'sys-shopping-product/:id/delete',
        component: SysShoppingProductDeletePopupComponent,
        resolve: {
            sysShoppingProduct: SysShoppingProductResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysShoppingProduct.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
