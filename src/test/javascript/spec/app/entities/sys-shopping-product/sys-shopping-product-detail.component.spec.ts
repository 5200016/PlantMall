/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysShoppingProductDetailComponent } from 'app/entities/sys-shopping-product/sys-shopping-product-detail.component';
import { SysShoppingProduct } from 'app/shared/model/sys-shopping-product.model';

describe('Component Tests', () => {
    describe('SysShoppingProduct Management Detail Component', () => {
        let comp: SysShoppingProductDetailComponent;
        let fixture: ComponentFixture<SysShoppingProductDetailComponent>;
        const route = ({ data: of({ sysShoppingProduct: new SysShoppingProduct(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysShoppingProductDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysShoppingProductDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysShoppingProductDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysShoppingProduct).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
