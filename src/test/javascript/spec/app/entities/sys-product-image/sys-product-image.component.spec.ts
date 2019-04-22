/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysProductImageComponent } from 'app/entities/sys-product-image/sys-product-image.component';
import { SysProductImageService } from 'app/entities/sys-product-image/sys-product-image.service';
import { SysProductImage } from 'app/shared/model/sys-product-image.model';

describe('Component Tests', () => {
    describe('SysProductImage Management Component', () => {
        let comp: SysProductImageComponent;
        let fixture: ComponentFixture<SysProductImageComponent>;
        let service: SysProductImageService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysProductImageComponent],
                providers: []
            })
                .overrideTemplate(SysProductImageComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysProductImageComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysProductImageService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysProductImage(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysProductImages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
