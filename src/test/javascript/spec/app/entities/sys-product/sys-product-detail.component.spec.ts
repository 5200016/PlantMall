/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysProductDetailComponent } from 'app/entities/sys-product/sys-product-detail.component';
import { SysProduct } from 'app/shared/model/sys-product.model';

describe('Component Tests', () => {
    describe('SysProduct Management Detail Component', () => {
        let comp: SysProductDetailComponent;
        let fixture: ComponentFixture<SysProductDetailComponent>;
        const route = ({ data: of({ sysProduct: new SysProduct(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysProductDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysProductDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysProductDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysProduct).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
